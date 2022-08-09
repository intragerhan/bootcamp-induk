package com.bootcamp.induk.utils;

import com.bootcamp.induk.domain.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("UserValidator.validate() is called");

		UserDto userDto = (UserDto) target;
		
		String id = userDto.getId();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
		
		if(id == null || id.length() < 4 || id.length() > 12) {
			errors.rejectValue("id", "invalidLength", new String[]{"5","12"}, null);
		}
	}

}