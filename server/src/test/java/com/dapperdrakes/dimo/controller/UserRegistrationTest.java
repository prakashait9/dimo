package com.dapperdrakes.dimo.controller;

import com.dapperdrakes.dimo.model.Language;
import com.dapperdrakes.dimo.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserRegistrationTest {

    @Test
    public void testForNullFirstName() {
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User(null, "last", "email", "pwd", Language.English);
        assertEquals("", userRegistration.signUp(testUser));
    }

    @Test
    public void testForBlankFirstName() {
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("", "last", "email", "pwd", Language.English);
        assertEquals("", userRegistration.signUp(testUser));
    }

    @Test
    public void testForValidFirstName() {
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("Ram", "last", "email", "pwd", Language.English);
        assertEquals("", userRegistration.signUp(testUser));
    }
}