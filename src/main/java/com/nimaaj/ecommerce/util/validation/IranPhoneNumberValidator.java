package com.nimaaj.ecommerce.util.validation;

import com.nimaaj.ecommerce.util.validation.annotation.IranPhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IranPhoneNumberValidator implements ConstraintValidator<IranPhoneNumber, CharSequence> {

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {

        if (charSequence != null && charSequence.length() > 0) {
            String strVal = charSequence.toString();
            if (strVal.length() == 11 && strVal.startsWith("09")) {
                return true;
            } else {
                return false;
            }
        }

        return true;
    }

    @Override
    public void initialize(IranPhoneNumber constraintAnnotation) {
    }
}
