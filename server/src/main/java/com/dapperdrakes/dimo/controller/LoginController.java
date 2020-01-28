package com.dapperdrakes.dimo.controller;

import com.dapperdrakes.dimo.model.UserLoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class LoginController {

    @PostMapping("/signUp")
    public String validateLogin(@RequestBody UserLoginRequest loginRequest){
        return "Login Success";
    }

}
