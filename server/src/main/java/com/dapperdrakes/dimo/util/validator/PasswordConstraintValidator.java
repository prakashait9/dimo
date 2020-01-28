package com.dapperdrakes.dimo.util.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.*;

import com.google.common.base.Joiner;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(final ValidPassword arg0) {

    }

    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext context) {
        // @formatter:off
        final PasswordValidator validator = new PasswordValidator(Arrays.asList(
            new LengthRule(8, 30),
            new AlphabeticalCharacterRule(1),
            new DigitCharacterRule(1)));
            //new WhitespaceRule())); new AlphabeticalSequenceRule(3,false),new SpecialCharacterRule(0),new UppercaseCharacterRule(0), new QwertySequenceRule(3,false), new NumericalSequenceRule(3,false)
        final RuleResult result = validator.validate(new PasswordData(password));
       if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(Joiner.on(",").join(validator.getMessages(result))).addConstraintViolation();
        return false;
    }

}
