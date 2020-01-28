package com.dapperdrakes.dimo.controller;


import com.dapperdrakes.dimo.model.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiMoUserRegistrationControllerTest {
    static String validToken = "0000";

    @Before
    //TBD : Delete existing records from table for users we are creating
    //something like this
    //UserDto UserDto = new UserDto(UserDto("Ram", "Singh", "firstname@gmail.com", "pwd", "pwd");
    //user.Delete();

    @After
    //TBD : Delete all records we created in test
    //something like this
    //UserDto UserDto = new UserDto(UserDto("Ram", "Singh", "firstname@gmail.com", "pwd", "pwd");
    //user.Delete();

    @Test
    public void testForNullFirstName() {
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto(null, "last", "email","pwd", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForBlankFirstName() {
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("", "last", "email", "pwd", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForExceedingLengthOfFirstName() {    //51 Characters
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("t0RrORl6O1nC3MXAWFyEVJfXQGtRAWxsHUFkVoEIMaC6uTwQOe", "Singh", "email", "pwd", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForValidFirstName() {
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("Ram", "Singh", "firstname@gmail.com", "pwd", "pwd");
        assertEquals(validToken, userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForNullLastName() {
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("first", null, "email", "pwd", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForBlankLastName() {
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("first", "", "email", "pwd", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForExceedingLengthOfLastName() {    //51 Characters
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("Ram", "t0RrORl6O1nC3MXAWFyEVJfXQGtRAWxsHUFkVoEIMaC6uTwQOe", "email", "pwd", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForValidLastName() {
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("Ram", "Singh", "lastname@gmail.com", "pwd", "pwd");
        assertEquals(validToken, userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForInvalidEmailId(){
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("Ram", "Singh", "email", "pwd", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
        testUserDto = new UserDto("Ram", "Singh", "email@gmail", "pwd", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
        testUserDto = new UserDto("Ram", "Singh", "gmail.com", "pwd", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
        testUserDto = new UserDto("Ram", "Singh", "email@gmail.", "pwd", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
        testUserDto = new UserDto("Ram", "Singh", "com.gmail@email", "pwd", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
        testUserDto = new UserDto("Ram", "Singh", "email@.com", "pwd", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForValidEmailId(){
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("Ram", "Singh", "validemail@gmail.com", "pwd", "pwd");
        assertEquals(validToken, userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForRepeatingEmailId(){
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("Ram", "Singh", "validemail@gmail.com", "pwd", "pwd");
        userRegistrationController.registerUserAccount(testUserDto);
        UserDto repeatUserDto = new UserDto("Laxman", "Singh", "validemail@gmail.com", "pwd", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForExceedingLengthOfEmail() {   //257 Characters
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("Ram", "Ram", "JhWmOn3uf8O3lmYyD37HmWpRrG9XWoK3HkEsHwdgZnZhXOf2PrPa1hUOZ7KXJhZPBSRzAVNoiNteS9JOnVwCtjd6bzonVFdD23JgnyYY5DU9pnKJJ7zsFtzbydszAZHnxsFIJDUpYYaruQfDyzuMePfKrhF9NjDD3wcTamuRnqfzsCZjDU5V3GXgBOHiozN1B0yTdqf3Dl4tFZaE1Js2dKp3EXA0@snfiHlju8q8L0.JeTzmjruvcXQft8DdZrzr3", "pwd", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForSmallLengthPassword(){
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("Ram", "Singh", "passwordTest@gmail.com", "pwd", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForNoNumberPassword(){
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("Ram", "Singh", "passwordTest@gmail.com", "pwdqw@rtyui", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForNoAlphabetsPassword(){
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("Ram", "Singh", "passwordTest@gmail.com", "123456@789#", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForNoSpecialCharPassword(){
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("Ram", "Singh", "passwordTest@gmail.com", "pwd1234567", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForNoNumberAndAlphabetPassword(){
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("Ram", "Singh", "passwordTest@gmail.com", "!#$$^$^%#^##@", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForNoNumberAndSpecialCharPassword(){
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("Ram", "Singh", "passwordTest@gmail.com", "abcdefghijkl", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForNoSpecialCharAndAlphabetPassword(){
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("Ram", "Singh", "passwordTest@gmail.com", "2334564324754", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForValidPassword(){
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("Ram", "Singh", "passwordTest@gmail.com", "randomtext@123", "pwd");
        assertEquals(validToken, userRegistrationController.registerUserAccount(testUserDto));
    }

    @Test
    public void testForExceedingLengthOfPassword(){ //33 Characters
        UserRegistrationController userRegistrationController = new UserRegistrationController();
        UserDto testUserDto = new UserDto("Ram", "Singh", "passwordTest@gmail.com", "bR7SQFVOXeU@H1MV1RFczZA1nBgqbPYve", "pwd");
        assertEquals("", userRegistrationController.registerUserAccount(testUserDto));
    }

}