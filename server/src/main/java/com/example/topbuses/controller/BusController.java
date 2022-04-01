package com.example.topbuses.controller;

import com.example.topbuses.model.Bus;
import com.example.topbuses.model.BusStop;
import com.example.topbuses.service.BusDataProvider;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    private final Logger logger = LoggerFactory.getLogger(BusController.class);

    BusDataProvider provider;

    public BusController(BusDataProvider provider) {
        this.provider = provider;
    }

    @GetMapping("/top")
    public Iterable<Bus> getTop() {
        logger.info("Data {}", provider.getBusJourneys().block());

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
