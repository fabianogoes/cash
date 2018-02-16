package com.cash.service;

import com.cash.model.Login;
import com.cash.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean valid(Login login) {
        User user = userService.findByEmail(login.getEmail());
        if(passwordEncoder.matches(login.getPassword(), user.getPassword())){
            return true;
        }
        return false;
    }

    public User getUser(Login login) {
        return userService.findByEmail(login.getEmail());
    }
}
