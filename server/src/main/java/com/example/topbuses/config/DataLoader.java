package com.example.topbuses.config;

import com.example.topbuses.service.BusService;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final BusService service;

    public DataLoader(BusService service) {
        this.service = service;
    }

    @PostConstruct
    private void load() {
        service.load();
    }
}
