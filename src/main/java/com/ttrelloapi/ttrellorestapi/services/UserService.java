package com.ttrelloapi.ttrellorestapi.services;


import com.ttrelloapi.ttrellorestapi.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Users addOrSaveTask(Users user);

}