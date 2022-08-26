package fr.societegenerale.exception;

import java.text.MessageFormat;

public class AccountNotContainAmountException extends Exception {


    public AccountNotContainAmountException(String accountNumber, Double amount) {
        super(MessageFormat.format("The account {0} not contain the amount {1}", accountNumber, amount));
    }
}
