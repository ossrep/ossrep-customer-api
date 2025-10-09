package com.ossrep.individualcustomer;

public enum Suffix {

    JR("Jr."),
    SR("Sr."),
    III("III"),
    IV("IV"),
    V("V"),
    VI("VI"),
    VII("VII"),
    VIII("VIII"),
    IX("IX"),
    X("X");

    private final String displayValue;

    Suffix(String displayValue) {
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
