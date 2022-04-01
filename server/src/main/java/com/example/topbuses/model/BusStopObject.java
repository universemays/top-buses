package com.example.topbuses.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusStopObject {

    @JsonProperty("StopPointNumber")
    private String number;

    @JsonProperty("StopPointName")
    private String name;

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
