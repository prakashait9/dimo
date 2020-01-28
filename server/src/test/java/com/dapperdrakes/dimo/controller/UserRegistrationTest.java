package com.dapperdrakes.dimo.controller;

import com.dapperdrakes.dimo.model.Language;
import com.dapperdrakes.dimo.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserRegistrationTest {
    static String validToken = "0000";

    @Before
    //TBD : Delete existing records from table for users we are creating
    //something like this
    //User user = new User(User("Ram", "Singh", "firstname@gmail.com", "pwd", "pwd");
    //user.Delete();

    @After
    //TBD : Delete all records we created in test
    //something like this
    //User user = new User(User("Ram", "Singh", "firstname@gmail.com", "pwd", "pwd");
    //user.Delete();

    @Test
    public void testForNullFirstName() {
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User(null, "last", "email","pwd", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForBlankFirstName() {
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("", "last", "email", "pwd", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForExceedingLengthOfFirstName() {    //51 Characters
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("t0RrORl6O1nC3MXAWFyEVJfXQGtRAWxsHUFkVoEIMaC6uTwQOe", "Singh", "email", "pwd", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForValidFirstName() {
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("Ram", "Singh", "firstname@gmail.com", "pwd", "pwd");
        assertEquals(validToken, userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForNullLastName() {
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("first", null, "email", "pwd", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForBlankLastName() {
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("first", "", "email", "pwd", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForExceedingLengthOfLastName() {    //51 Characters
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("Ram", "t0RrORl6O1nC3MXAWFyEVJfXQGtRAWxsHUFkVoEIMaC6uTwQOe", "email", "pwd", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForValidLastName() {
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("Ram", "Singh", "lastname@gmail.com", "pwd", "pwd");
        assertEquals(validToken, userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForInvalidEmailId(){
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("Ram", "Singh", "email", "pwd", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
        testUser = new User("Ram", "Singh", "email@gmail", "pwd", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
        testUser = new User("Ram", "Singh", "gmail.com", "pwd", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
        testUser = new User("Ram", "Singh", "email@gmail.", "pwd", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
        testUser = new User("Ram", "Singh", "com.gmail@email", "pwd", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
        testUser = new User("Ram", "Singh", "email@.com", "pwd", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForValidEmailId(){
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("Ram", "Singh", "validemail@gmail.com", "pwd", "pwd");
        assertEquals(validToken, userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForRepeatingEmailId(){
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("Ram", "Singh", "validemail@gmail.com", "pwd", "pwd");
        userRegistration.registerUserAccount(testUser);
        User repeatUser = new User("Laxman", "Singh", "validemail@gmail.com", "pwd", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForExceedingLengthOfEmail() {   //257 Characters
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("Ram", "Ram", "JhWmOn3uf8O3lmYyD37HmWpRrG9XWoK3HkEsHwdgZnZhXOf2PrPa1hUOZ7KXJhZPBSRzAVNoiNteS9JOnVwCtjd6bzonVFdD23JgnyYY5DU9pnKJJ7zsFtzbydszAZHnxsFIJDUpYYaruQfDyzuMePfKrhF9NjDD3wcTamuRnqfzsCZjDU5V3GXgBOHiozN1B0yTdqf3Dl4tFZaE1Js2dKp3EXA0@snfiHlju8q8L0.JeTzmjruvcXQft8DdZrzr3", "pwd", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForSmallLengthPassword(){
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("Ram", "Singh", "passwordTest@gmail.com", "pwd", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForNoNumberPassword(){
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("Ram", "Singh", "passwordTest@gmail.com", "pwdqw@rtyui", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForNoAlphabetsPassword(){
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("Ram", "Singh", "passwordTest@gmail.com", "123456@789#", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForNoSpecialCharPassword(){
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("Ram", "Singh", "passwordTest@gmail.com", "pwd1234567", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForNoNumberAndAlphabetPassword(){
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("Ram", "Singh", "passwordTest@gmail.com", "!#$$^$^%#^##@", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForNoNumberAndSpecialCharPassword(){
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("Ram", "Singh", "passwordTest@gmail.com", "abcdefghijkl", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForNoSpecialCharAndAlphabetPassword(){
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("Ram", "Singh", "passwordTest@gmail.com", "2334564324754", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForValidPassword(){
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("Ram", "Singh", "passwordTest@gmail.com", "randomtext@123", "pwd");
        assertEquals(validToken, userRegistration.registerUserAccount(testUser));
    }

    @Test
    public void testForExceedingLengthOfPassword(){ //33 Characters
        UserRegistration userRegistration = new UserRegistration();
        User testUser = new User("Ram", "Singh", "passwordTest@gmail.com", "bR7SQFVOXeU@H1MV1RFczZA1nBgqbPYve", "pwd");
        assertEquals("", userRegistration.registerUserAccount(testUser));
    }

}