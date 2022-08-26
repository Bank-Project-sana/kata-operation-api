package fr.societegenerale.exception;

import java.text.MessageFormat;

public class NotFoundException extends Exception {
    public NotFoundException(String entityName) {
        super(MessageFormat.format("the entity {0} not found", entityName));
    }
}
