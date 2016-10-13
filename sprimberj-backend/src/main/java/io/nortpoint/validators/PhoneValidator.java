package io.nortpoint.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	@Override
	public void initialize(Phone arg0) {
	}

	@Override
	public boolean isValid(String phoneNumber, ConstraintValidatorContext ctx) {
		return phoneNumber.matches("[0-9]{2}\\-[0-9]{3}\\-[0-9]{2}");
	}

}