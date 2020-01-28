package com.dapperdrakes.dimo.controller;

import com.dapperdrakes.dimo.model.User;
import com.dapperdrakes.dimo.service.IUserService;
import com.dapperdrakes.dimo.service.UserService;
import com.dapperdrakes.dimo.util.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserRegistration {

    @Autowired
    IUserService userService;


    @PostMapping(value = "/user/signup")
    @ResponseBody
    public GenericResponse registerUserAccount(@Valid @RequestBody User accountInfo) {

        final User registered = userService.registerNewUserAccount(accountInfo);
       // eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
    }
}
