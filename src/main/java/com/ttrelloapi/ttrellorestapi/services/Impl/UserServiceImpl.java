package com.ttrelloapi.ttrellorestapi.services.Impl;

import com.ttrelloapi.ttrellorestapi.entities.Users;
import com.ttrelloapi.ttrellorestapi.repo.UserRepository;
import com.ttrelloapi.ttrellorestapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(s);
        if(user!=null){
            return user;
        }else{
            throw new UsernameNotFoundException("USER NOT FOUND");
        }
    }

    @Override
    public Users addOrSaveTask(Users user) {
        return userRepository.save(user);
    }
}