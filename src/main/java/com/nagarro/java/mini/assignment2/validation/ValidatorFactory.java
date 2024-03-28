package com.nagarro.java.mini.assignment2.validation;

public class ValidatorFactory {
	
	public static Object getValidator(String input) {
        if (new NumericValidator().validate(input)) {
            return new NumericValidator();
        } else if (new EnglishAlphabetsValidator().validate(input)) {
            return new EnglishAlphabetsValidator();
        } else {
            throw new IllegalArgumentException("Invalid input type");
        }
    }

}
