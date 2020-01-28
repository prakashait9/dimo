package com.dapperdrakes.dimo.controller;


import com.dapperdrakes.dimo.dao.model.DiMoUser;
import com.dapperdrakes.dimo.model.UserDto;
import com.dapperdrakes.dimo.service.IUserService;
import com.dapperdrakes.dimo.util.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserRegistrationController {

    @Autowired
    IUserService userService;


    @PostMapping(value = "/signup")
    public GenericResponse registerUserAccount(@Valid @RequestBody UserDto accountInfo) {

        final DiMoUser registered = userService.registerNewUserAccount(accountInfo);
       // eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
    }
}
