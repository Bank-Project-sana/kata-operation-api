package fr.societegenerale.exception;

import java.text.MessageFormat;

public class InvalidInputException extends Exception {
    public InvalidInputException(String inputName) {
        super(MessageFormat.format("The input {0} is invalid", inputName));
    }
}
