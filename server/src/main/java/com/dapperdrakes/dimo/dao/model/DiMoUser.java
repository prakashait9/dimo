package com.dapperdrakes.dimo.dao.model;


import com.dapperdrakes.dimo.model.UserDto;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class DiMoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
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

    public DiMoUser(UserDto userDto) {
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
    }
}
