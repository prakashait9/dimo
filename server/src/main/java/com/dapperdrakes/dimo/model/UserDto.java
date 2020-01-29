package com.dapperdrakes.dimo.model;

import com.dapperdrakes.dimo.util.validator.ValidEmail;
import com.dapperdrakes.dimo.util.validator.ValidPassword;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

    @NotNull(message = "First name cannot be missing or empty")
    @Size(min = 2, message = "First name must not be less than 2 characters")
    @Size(max = 50, message = "First name must not be more than 50 characters")
    private String firstName;

    @Size(max = 50, message = "Last name must not be more than 50 characters")
    private String lastName;

    @ValidPassword
    private String password;

    @NotNull(message = "Email cannot be missing or empty")
    @Size(max = 256, message = "Email must not be more than 256 characters")
    @ValidEmail
    private String email;

    public UserDto() {
    }

    public UserDto(@NotNull(message = "First name cannot be missing or empty")
                   @Size(min = 2, message = "First name must not be less than 2 characters")
                   @Size(max = 50, message = "First name must not be more than 50 characters")
                           String firstName,
                   @Size(max = 50, message = "Last name must not be more than 50 characters")
                           String lastName,
                   @NotNull(message = "Email cannot be missing or empty")
                   @Size(max = 256, message = "Email must not be more than 256 characters") String email,
                   String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

}
