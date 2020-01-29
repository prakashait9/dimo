package com.dapperdrakes.dimo.controller;

import com.dapperdrakes.dimo.model.UserDto;
import com.dapperdrakes.dimo.service.IUserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
//@WebIntegrationTest
@Ignore
public class DataRepositoryTest {
    private Validator validator;
    DiMoUserController userRegistrationController;
    //@Before
    //TBD : Delete existing records from table for users we are creating
    //something like this
    //UserDto UserDto = new UserDto(UserDto("Ram", "Singh", "firstname@gmail.com", "pwd", "pwd");
    //user.Delete();

    @After
    //TBD : Delete all records we created in test
    //something like this
    //UserDto UserDto = new UserDto(UserDto("Ram", "Singh", "firstname@gmail.com", "pwd", "pwd");
    //user.Delete();

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        IUserService userService = Mockito.mock(IUserService.class);
        userRegistrationController = new DiMoUserController();
        userRegistrationController.userService = userService;
    }

    @Test
    public void testForRepeatingEmailId() throws Exception {
        UserDto testUserDto = new UserDto("Ram", "Singh", "repeatingmail@gmail.com", "pwd@1234ee");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(testUserDto);
        userRegistrationController.registerUserAccount(testUserDto);
        assertTrue(violations.isEmpty());

        UserDto repeatUserDto = new UserDto("Laxman", "Singh", "repeatingmail@gmail.com", "pwd@1234ee");
        userRegistrationController.registerUserAccount(repeatUserDto);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation<UserDto> violation = violations.iterator().next();
        assertEquals("Last name must not be more than 50 characters", violation.getMessage());
    }
}
