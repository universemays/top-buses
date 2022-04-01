package com.example.topbuses.model;

@SuppressWarnings("unused")
public class BusStop {

    private final String number;
    private final String name;
    private final String directionCode;

    public BusStop(String number, String name, String directionCode) {
        this.number = number;
        this.name = name;
        this.directionCode = directionCode;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDirectionCode() {
        return directionCode;
    }
}
