package com.example.topbuses.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusJourneyObject {

    @JsonProperty("LineNumber")
    private String lineNumber;

    @JsonProperty("JourneyPatternPointNumber")
    private String stopNumber;

    @JsonProperty("DirectionCode")
    private String directionCode;

    public String getLineNumber() {
        return lineNumber;
    }

    public String getStopNumber() {
        return stopNumber;
    }

    public String getDirectionCode() {
        return directionCode;
    }
}
