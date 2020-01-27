package com.dapperdrakes.dimo.model;

public class User {
    String firstName;
    String lastName;
    String email;
    String password;
    Language language;

    public User(String firstName, String lastName, String email, String password, Language language){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.language = language;
    }
}
