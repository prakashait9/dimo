package com.dapperdrakes.dimo.controller;


import com.dapperdrakes.dimo.config.JwtTokenUtil;
import com.dapperdrakes.dimo.model.DiMoUser;
import com.dapperdrakes.dimo.model.UserDto;
import com.dapperdrakes.dimo.model.UserLoginRequest;
import com.dapperdrakes.dimo.service.IUserService;
import com.dapperdrakes.dimo.service.JwtUserDetailsService;
import com.dapperdrakes.dimo.model.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DiMoUserController {

    @Autowired
    IUserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;
    
    @PostMapping(value = "/api/signup", produces = "application/json")
    public GenericResponse registerUserAccount(@Valid @RequestBody UserDto accountInfo) throws Exception {
        final DiMoUser registered = userService.registerNewUserAccount(accountInfo);
        if(registered != null) {
            return createAuthenticationToken(new UserLoginRequest(accountInfo.getEmail(), accountInfo.getPassword()));
        }
        return null;
    }

    @PostMapping(value = "/api/login" , produces = "application/json")
    public GenericResponse createAuthenticationToken(@RequestBody UserLoginRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        return jwtTokenUtil.generateToken(userDetails);
    }

    private void authenticate(UserLoginRequest userLoginRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
