package com.example.topbuses.service;

import com.example.topbuses.model.Bus;
import com.example.topbuses.model.BusJourneyObject;
import com.example.topbuses.model.BusStop;
import com.example.topbuses.model.BusStopObject;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BusService {

    private final Logger logger = LoggerFactory.getLogger(BusService.class);

    private List<Bus> buses = Collections.emptyList();

    private final BusDataProvider dataProvider;

    public BusService(BusDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public void load() {
        List<BusStopObject> stops = dataProvider.getBusStops().block();
        List<BusJourneyObject> journeys = dataProvider.getBusJourneys().block();

        Map<String, BusStopObject> stopMap = Objects
            .requireNonNull(stops)
            .stream()
            .collect(Collectors.toMap(BusStopObject::getNumber, Function.identity()));

        Map<String, List<BusStop>> busStopGroupMap = Objects
            .requireNonNull(journeys)
            .stream()
            .collect(
                Collectors.groupingBy(
                    BusJourneyObject::getLineNumber,
                    Collectors.mapping(
                        journey -> {
                            BusStopObject stopObject = stopMap.get(journey.getStopNumber());

                            return new BusStop(
                                journey.getStopNumber(),
                                Optional.ofNullable(stopObject).map(BusStopObject::getName).orElse(null),
                                journey.getDirectionCode()
                            );
                        },
                        Collectors.toList()
                    )
                )
            );

        buses =
            busStopGroupMap
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(a -> a.getValue().size(), Comparator.reverseOrder()))
                .map(entry -> new Bus(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        logger.info("Successfully loaded {} buses", buses.size());
    }

    public List<Bus> topJourneyCountBuses(int numberOfBus) {
        return buses.subList(0, Math.min(Math.max(numberOfBus, 0), buses.size()));
    }
}
