package com.ttrelloapi.ttrellorestapi.controllers;

import com.ttrelloapi.ttrellorestapi.dto.JwtRequest;
import com.ttrelloapi.ttrellorestapi.dto.JwtResponse;
import com.ttrelloapi.ttrellorestapi.entities.Users;
import com.ttrelloapi.ttrellorestapi.jwt.JWTTokenGenerator;
import com.ttrelloapi.ttrellorestapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthController {

    @Autowired
    private JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/auth")
    public ResponseEntity<?> auth(@RequestBody JwtRequest request) throws Exception{

        authenticate(request.getEmail(), request.getPassword());
        final UserDetails userDetails =
                userService.loadUserByUsername(request.getEmail());

        final String token = jwtTokenGenerator.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));

    }

    public void authenticate(String email, String password) throws Exception{

        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        }catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }

    @RequestMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody Users users) throws Exception{

        Users user = new Users();
        user.setEmail(users.getEmail());
        user.setFullName(users.getFullName());
        user.setPassword(passwordEncoder.encode(users.getPassword()));
        try {
            userService.addOrSaveTask(user);
        }catch(Exception e){
            String error = "error";
            return ResponseEntity.ok(new JwtResponse(error));
        }
        authenticate(users.getEmail(), users.getPassword());
        final UserDetails userDetails =
                userService.loadUserByUsername(users.getEmail());

        final String token = jwtTokenGenerator.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));

    }

}