package com.dapperdrakes.dimo.model;

import com.dapperdrakes.dimo.util.validator.PasswordMatches;
import com.dapperdrakes.dimo.util.validator.ValidEmail;
import com.dapperdrakes.dimo.util.validator.ValidPassword;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatches
public class UserDto {
    @NotNull(message="First name cannot be missing or empty")
    @Size(min=2, message="First name must not be less than 2 characters")
    private String firstName;

    private String lastName;

    @ValidPassword
    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

    @NotNull(message="Email cannot be missing or empty")
    @ValidEmail
    private String email;


    public UserDto(){};

    public UserDto(@NotNull(message = "First name cannot be missing or empty") @Size(min = 2, message = "First name must not be less than 2 characters") String firstName, String lastName, @NotNull(message = "Email cannot be missing or empty") String email, String password, @NotNull @Size(min = 1) String matchingPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
