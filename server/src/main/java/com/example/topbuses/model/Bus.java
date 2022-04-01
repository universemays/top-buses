package com.example.topbuses.model;

import java.util.List;

@SuppressWarnings("unused")
public class Bus {

    private final String number;
    private final List<BusStop> stops;

    public Bus(String number, List<BusStop> stops) {
        this.number = number;
        this.stops = stops;
    }

    public String getNumber() {
        return number;
    }

    public List<BusStop> getStops() {
        return stops;
    }
}
