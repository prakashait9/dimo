package com.dapperdrakes.dimo.model;

public class User {
    String firstName;
    String lastName;
    String email;
    Language language;

    public User(String firstName, String lastName, String email, Language language){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.language = language;
    }
}
