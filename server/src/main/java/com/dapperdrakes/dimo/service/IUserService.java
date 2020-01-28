package com.dapperdrakes.dimo.service;


import com.dapperdrakes.dimo.error.UserAlreadyExistException;
import com.dapperdrakes.dimo.model.User;

public interface IUserService {

    User registerNewUserAccount(User accountDto) throws UserAlreadyExistException;

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    //void deleteUser(User user);


}
