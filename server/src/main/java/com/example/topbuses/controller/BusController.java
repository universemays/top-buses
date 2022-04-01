package com.example.topbuses.controller;

import com.example.topbuses.model.Bus;
import com.example.topbuses.model.BusStop;
import com.example.topbuses.service.BusService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    BusService service;

    public BusController(BusService service) {
        this.service = service;
    }

    @GetMapping("/top")
    public Iterable<Bus> getTop() {
        service.load();

        return List.of(
            new Bus(
                "1",
                List.of(
                    new BusStop("12345", "Anywhere A", "1"), //
                    new BusStop("12346", "Anywhere B", "2")
                )
            )
        );
    }
}
