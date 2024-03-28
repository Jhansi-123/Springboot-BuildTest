package com.nagarro.java.mini.assignment2.validation;

public class EnglishAlphabetsValidator implements Validator {
    private static final EnglishAlphabetsValidator instance = new EnglishAlphabetsValidator();

    EnglishAlphabetsValidator() {}

    public static EnglishAlphabetsValidator getInstance() {
        return instance;
    }

    @Override
    public boolean validate(String input) {

        return input.matches("^[a-zA-Z]*$");
    }
}
