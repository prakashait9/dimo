package com.dapperdrakes.dimo.model;


import com.dapperdrakes.dimo.model.UserDto;
import com.dapperdrakes.dimo.model.UserLoginRequest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Document
public class DiMoUser {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String createdOn;
    private String updatedOn;
    private String lastVisited;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public DiMoUser() {
    }

    public DiMoUser(UserDto userDto) {
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(userDto.getPassword());
    }

    public UserLoginRequest getLoginRequest() {

        return new UserLoginRequest(email, password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
