package com.ossrep.customer;

public enum CustomerType {

    BUSINESS("Business"),
    INDIVIDUAL("Individual");

    private final String displayValue;

    CustomerType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    @Override
    public String toString() {
        return displayValue;
    }

}
