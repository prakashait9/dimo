package com.dapperdrakes.dimo.controller;

import com.dapperdrakes.dimo.model.UserLoginRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.atLeast;

public class DiMoUserLoginTest {

    @Test
    public void testForNullEmail() {
        LoginController loginController = new LoginController();
        UserLoginRequest userRegistration = new UserLoginRequest(null, "qwerty@1234");
        assertEquals("Email should be valid", loginController.validateLogin(userRegistration));
    }

    @Test
    public void testForEmptyEmail() {
        LoginController loginController = new LoginController();
        UserLoginRequest userRegistration = new UserLoginRequest("", "qwerty@1234");
        verify(loginController, atLeast(1)).validateLogin(userRegistration);
        //assertEquals("Email should be valid", loginController.validateLogin(userRegistration));
    }

    @Test
    public void testForInValidLoginEmail() {
        LoginController loginController = new LoginController();
        UserLoginRequest userRegistration = new UserLoginRequest("email", "qwerty@1234");
        assertEquals("", loginController.validateLogin(userRegistration));
        userRegistration = new UserLoginRequest("email@gmail", "qwerty@1234");
        assertEquals("", loginController.validateLogin(userRegistration));
        userRegistration = new UserLoginRequest("gmail.com", "qwerty@1234");
        assertEquals("", loginController.validateLogin(userRegistration));
        userRegistration = new UserLoginRequest("email@gmail.", "qwerty@1234");
        assertEquals("", loginController.validateLogin(userRegistration));
        userRegistration = new UserLoginRequest("com.gmail@email", "qwerty@1234");
        assertEquals("", loginController.validateLogin(userRegistration));
        userRegistration = new UserLoginRequest("email@.coml", "qwerty@1234");
        assertEquals("", loginController.validateLogin(userRegistration));
    }

    @Test
    public void testForValidLoginEmail() {
        LoginController loginController = new LoginController();
        UserLoginRequest userRegistration = new UserLoginRequest("nithin@gmail.com", "qwerty@1234");
        verify(loginController, atLeast(1)).validateLogin(userRegistration);
        //assertEquals("", loginController.validateLogin(userRegistration));
    }

    @Test
    public void testForExceedingLoginEmailId() {   //257 Characters
        LoginController loginController = new LoginController();
        UserLoginRequest userRegistration = new UserLoginRequest("JhWmOn3uf8O3lmYyD37HmWpRrG9XWoK3HkEsHwdgZnZhXOf2PrPa1hUOZ7KXJhZPBSRzAVNoiNteS9JOnVwCtjd6bzonVFdD23JgnyYY5DU9pnKJJ7zsFtzbydszAZHnxsFIJDUpYYaruQfDyzuMePfKrhF9NjDD3wcTamuRnqfzsCZjDU5V3GXgBOHiozN1B0yTdqf3Dl4tFZaE1Js2dKp3EXA0@snfiHlju8q8L0.JeTzmjruvcXQft8DdZrzr3", "pwd");
        assertEquals("Email should be valid", loginController.validateLogin(userRegistration));
    }

    @Test
    public void testForValidPassword() {
        LoginController loginController = new LoginController();
        UserLoginRequest userRegistration = new UserLoginRequest("passwordTest@gmail.com", "randomtext@123");
        verify(loginController, atLeast(1)).validateLogin(userRegistration);
        //assertEquals("", loginController.validateLogin(userRegistration));
    }

    @Test
    public void testForNullPassword() {
        LoginController loginController = new LoginController();
        UserLoginRequest userRegistration = new UserLoginRequest("passwordTest@gmail.com", null);
        assertEquals("Password should be valid", loginController.validateLogin(userRegistration));
    }

    @Test
    public void testForEmptyPassword() {
        LoginController loginController = new LoginController();
        UserLoginRequest userRegistration = new UserLoginRequest("passwordTest@gmail.com", "");
        assertEquals("Password should be valid", loginController.validateLogin(userRegistration));
    }

    @Test
    public void testForMinPasswordLength() { //8 Characters
        LoginController loginController = new LoginController();
        UserLoginRequest userRegistration = new UserLoginRequest("passwordTest@gmail.com", "asdfsad");
        assertEquals("Password should be valid", loginController.validateLogin(userRegistration));
    }

    @Test
    public void testForExceedingLengthOfPassword() { //33 Characters
        LoginController loginController = new LoginController();
        UserLoginRequest userRegistration = new UserLoginRequest("passwordTest@gmail.com", "bR7SQFVOXeU@H1MV1RFczZA1nBgqbPYve");
        assertEquals("Password should be valid", loginController.validateLogin(userRegistration));
    }

    @Test
    public void testForPasswordWithNoNumber() {
        LoginController loginController = new LoginController();
        UserLoginRequest userRegistration = new UserLoginRequest("passwordTest@gmail.com", "qwerty@abc");
        assertEquals("Password should be valid", loginController.validateLogin(userRegistration));
    }

    @Test
    public void testForPasswordWithNoAlphabet() {
        LoginController loginController = new LoginController();
        UserLoginRequest userRegistration = new UserLoginRequest("passwordTest@gmail.com", "12345@1234");
        assertEquals("Password should be valid", loginController.validateLogin(userRegistration));
    }

    @Test
    public void testForPasswordWithNoSpecialCharacter() {
        LoginController loginController = new LoginController();
        UserLoginRequest userRegistration = new UserLoginRequest("passwordTest@gmail.com", "acbdf1234");
        assertEquals("Password should be valid", loginController.validateLogin(userRegistration));
    }

    /*@Test
    public void testPasswordValidator() {
        PasswordValidator validator = new PasswordValidator();
        UserLoginRequest userRegistration = new UserLoginRequest("passwordTest@gmail.com", "bR7SQFVOXeU@H1MV1RFczZA1nBgqbPYve");
        assertFalse(validator.validate(null));
        assertFalse(validator.validate(""));
        assertFalse(validator.validate("aaaaaa"));
        assertFalse(validator.validate("qqqqqqqqqq"));
        assertFalse(validator.validate("qqqqqqqqqq@"));
        assertFalse(validator.validate("cdanjt189"));
        assertTrue(validator.validate("cdanjt@189"));
        assertTrue(validator.validate("cdanjt*#%@189"));
    }

    @Test
    public void testForInValidLoginEmailUsingValidator() {
        EmailValidator emailValidator = new EmailValidator();
        LoginController loginController = new LoginController();

        UserLoginRequest userRegistration = new UserLoginRequest("email", "qwerty@1234");
        assertFalse(emailValidator.validateEmail(userRegistration.getEmail()));
        userRegistration = new UserLoginRequest("email@gmail", "qwerty@1234");
        assertFalse(emailValidator.validateEmail(userRegistration.getEmail()));
        userRegistration = new UserLoginRequest("gmail.com", "qwerty@1234");
        assertFalse(emailValidator.validateEmail(userRegistration.getEmail()));
        userRegistration = new UserLoginRequest("email@gmail.", "qwerty@1234");
        assertFalse(emailValidator.validateEmail(userRegistration.getEmail()));
        userRegistration = new UserLoginRequest("com.gmail@email", "qwerty@1234");
        assertFalse(emailValidator.validateEmail(userRegistration.getEmail()));
        userRegistration = new UserLoginRequest("email@.coml", "qwerty@1234");
        assertFalse(emailValidator.validateEmail(userRegistration.getEmail()));

    }*/
}
