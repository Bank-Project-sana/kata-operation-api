package fr.societegenerale.model;

import java.io.Serializable;

public enum OperationTypeEnum implements Serializable {
    WITHDRAWAL,

    DEPOSIT;

    public String getOperationType() {
        return this.name();
    }
}
