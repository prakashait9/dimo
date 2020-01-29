package com.dapperdrakes.dimo.controller;


import com.dapperdrakes.dimo.model.UserDto;
import com.dapperdrakes.dimo.service.IUserService;
import org.junit.Before;

import org.junit.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.*;

public class DiMoUserControllerTest {
    static String validToken = "0000";
    private Validator validator;
    DiMoUserController userRegistrationController;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        IUserService userService = Mockito.mock(IUserService.class);
        userRegistrationController = new DiMoUserController();
        userRegistrationController.userService = userService;
    }

    @Test
    public void testForNullFirstName() throws Exception {
        UserDto testUserDto = new UserDto(null, "last", "email@gmail.com", "pwd@123434");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation<UserDto> violation = violations.iterator().next();
        assertEquals("First name cannot be missing or empty", violation.getMessage());
    }

    @Test
    public void testForBlankFirstName() throws Exception {
        UserDto testUserDto = new UserDto("", "last", "email@gmail.com", "pwd@123434"
        );
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation<UserDto> violation = violations.iterator().next();
        assertEquals("First name must not be less than 2 characters", violation.getMessage());
    }

    @Test
    public void testForExceedingLengthOfFirstName() throws Exception {    //51 Characters
        UserDto testUserDto = new UserDto("t0RrORl6O1nC3MXAWFyEVJfXQGtRAWxsHUFkVoEIMaC6uTwQOedfgsdfgsdfgsdf",
                "Singh", "email@gmail.com", "pwd@123434");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation<UserDto> violation = violations.iterator().next();
        assertEquals("First name must not be more than 50 characters", violation.getMessage());
    }

    @Test
    public void testForValidFirstName() throws Exception {
        UserDto testUserDto = new UserDto("Ram", "Singh", "firstname@gmail.com",
                "pwd@12345");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void testForNullLastName() throws Exception {
        UserDto testUserDto = new UserDto("first", null, "email@gmail.com", "pwd@123434");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void testForBlankLastName() throws Exception {
        UserDto testUserDto = new UserDto("first", "", "email@gmail.com", "pwd@123434");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void testForExceedingLengthOfLastName() throws Exception {    //51 Characters
        UserDto testUserDto = new UserDto("ram", "t0RrORl6O1nC3MXAWFyEVJfXQGtRAWxsHUFkVoEIMaC6uTwQOedfgsdfgsdfgsdf",
                "email@gmail.com", "pwd@123434");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation<UserDto> violation = violations.iterator().next();
        assertEquals("Last name must not be more than 50 characters", violation.getMessage());
    }

    @Test
    public void testForValidLastName() throws Exception {
        UserDto testUserDto = new UserDto("Ram", "Singh", "lastname@gmail.com",
                "pwd@12345");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void testForInvalidEmailId() throws Exception {
        UserDto testUserDto = new UserDto("Ram", "Singh", "email", "pwd@1234ee");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation<UserDto> violation = violations.iterator().next();
        assertEquals("Invalid Email", violation.getMessage());


        testUserDto = new UserDto("Ram", "Singh", "email@gmail", "pwd@1234ee");
        userRegistrationController.registerUserAccount(testUserDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        violation = violations.iterator().next();
        assertEquals("Invalid Email", violation.getMessage());


        testUserDto = new UserDto("Ram", "Singh", "gmail.com", "pwd@1234ee");
        userRegistrationController.registerUserAccount(testUserDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        violation = violations.iterator().next();
        assertEquals("Invalid Email", violation.getMessage());


        testUserDto = new UserDto("Ram", "Singh", "email@gmail.", "pwd@1234ee");
        userRegistrationController.registerUserAccount(testUserDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        violation = violations.iterator().next();
        assertEquals("Invalid Email", violation.getMessage());


        testUserDto = new UserDto("Ram", "Singh", "com.gmail@email", "pwd@1234ee");
        userRegistrationController.registerUserAccount(testUserDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        violation = violations.iterator().next();
        assertEquals("Invalid Email", violation.getMessage());


        testUserDto = new UserDto("Ram", "Singh", "email@.com", "pwd@1234ee");
        userRegistrationController.registerUserAccount(testUserDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        violation = violations.iterator().next();
        assertEquals("Invalid Email", violation.getMessage());
    }

    @Test
    public void testForValidEmailId() throws Exception {
        UserDto testUserDto = new UserDto("Ram", "Singh", "validemail@gmail.com", "pwd@1234ee");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void testForExceedingLengthOfEmail() throws Exception {   //257 Characters
        UserDto testUserDto = new UserDto("Ram", "Singh",
                "JhWmOn3uf8O3lmYyD37HmWpRrG9XWoK3HkEsHwdgZnZhXOf2PrPa1hUOZ7KXJhZPBSRzAVNoiNteS9JOnVwCtjd6bzonVFdD23JgnyYY5DU9pnKJJ7zsFtzbydszAZHnxsFIJDUpYYaruQfDyzuMePfKrhF9NjDD3wcTamuRnqfzsCZjDU5V3GXgBOHiozN1B0yTdqf3Dl4tFZaE1Js2dKp3EXA0dsnfiHlju8q8L0fJeTzmjruvcXQft@gmail.com",
                "pwd@1234ee");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation<UserDto> violation = violations.iterator().next();
        assertEquals("Email must not be more than 256 characters", violation.getMessage());
    }

    @Test
    public void testForSmallLengthPassword() throws Exception {
        UserDto testUserDto = new UserDto("Ram", "Singh", "passwordTest@gmail.com",
                "pwd");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation<UserDto> violation = violations.iterator().next();
        assertEquals("Password must be at least 8 characters in length.," +
                "Password must contain at least 1 digit characters.", violation.getMessage());
    }

    @Test
    public void testForNoNumberPassword() throws Exception {
        UserDto testUserDto = new UserDto("Ram", "Singh", "passwordTest@gmail.com",
                "pwdqw@rtyui");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation<UserDto> violation = violations.iterator().next();
        assertEquals("Password must contain at least 1 digit characters.", violation.getMessage());
    }

    @Test
    public void testForNoAlphabetsPassword() throws Exception {
        UserDto testUserDto = new UserDto("Ram", "Singh", "passwordTest@gmail.com",
                "123456@789#");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation<UserDto> violation = violations.iterator().next();
        assertEquals("Password must contain at least 1 alphabetical characters.", violation.getMessage());
    }

    @Test
    public void testForNoSpecialCharPassword() throws Exception {
        UserDto testUserDto = new UserDto("Ram", "Singh", "passwordTest@gmail.com",
                "pwd1234567");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void testForNoNumberAndAlphabetPassword() throws Exception {
        UserDto testUserDto = new UserDto("Ram", "Singh", "passwordTest@gmail.com",
                "!#$$^$^%#^##@");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation<UserDto> violation = violations.iterator().next();
        assertEquals("Password must contain at least 1 alphabetical characters.,Password must contain at" +
                " least 1 digit characters.", violation.getMessage());
    }

    @Test
    public void testForNoAlphabetInPassword() throws Exception {
        UserDto testUserDto = new UserDto("Ram", "Singh", "passwordTest@gmail.com",
                "2334564324754");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation<UserDto> violation = violations.iterator().next();
        assertEquals("Password must contain at least 1 alphabetical characters.", violation.getMessage());
    }

    @Test
    public void testForExceedingLengthOfPassword() throws Exception { //31 Characters
        UserDto testUserDto = new UserDto("Ram", "Singh", "passwordTest@gmail.com",
                "bR7SQFVOXeU@H1MV1RFczZA1nBgqbPYve");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation<UserDto> violation = violations.iterator().next();
        assertEquals("Password must be no more than 30 characters in length.", violation.getMessage());
    }

    @Test
    public void testForValidPassword() throws Exception {
        UserDto testUserDto = new UserDto("Ram", "Singh", "passwordTest@gmail.com",
                "randomtext@123");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);

        assertTrue(violations.isEmpty());
    }

}