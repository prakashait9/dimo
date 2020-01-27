package com.dapperdrakes.dimo.controller;

import com.dapperdrakes.dimo.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserRegistration {

    @PostMapping("/signUp")
    public String signUp(@RequestBody User user){
        return "0000";
    }
}
