package com.dapperdrakes.dimo.service;


import com.dapperdrakes.dimo.model.DiMoUser;
import com.dapperdrakes.dimo.error.UserAlreadyExistException;
import com.dapperdrakes.dimo.model.UserDto;

public interface IUserService {

    DiMoUser registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;

    UserDto getUser(String verificationToken);

    void saveRegisteredUser(UserDto user);

    //void deleteUser(User user);


}
