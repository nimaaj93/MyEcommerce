package com.nimaaj.ecommerce.util.validation;

import com.nimaaj.ecommerce.util.validation.annotation.SecurePassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class SecurePasswordValidator implements ConstraintValidator<SecurePassword, CharSequence> {

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (charSequence == null) {
            return true;
        }
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        return Pattern.matches(regex, charSequence);
    }
}