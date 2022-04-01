package com.example.topbuses.service;

import com.example.topbuses.execption.NoDataException;
import com.example.topbuses.model.BusJourneyObject;
import com.example.topbuses.model.BusStopObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BusDataProvider {

    private final Logger logger = LoggerFactory.getLogger(BusDataProvider.class);

    @Value("${api.key}")
    private String apiKey;

    private final WebClient apiClient;

    public BusDataProvider(WebClient apiClient) {
        this.apiClient = apiClient;
    }

    public Mono<List<BusStopObject>> getBusStops() {
        ParameterizedTypeReference<Response<BusStopObject>> typeReference = new ParameterizedTypeReference<>() {};
        return fetch("stop", typeReference);
    }

    public Mono<List<BusJourneyObject>> getBusJourneys() {
        ParameterizedTypeReference<Response<BusJourneyObject>> typeReference = new ParameterizedTypeReference<>() {};
        return fetch("jour", typeReference);
    }

    private <T> Mono<List<T>> fetch(String model, ParameterizedTypeReference<Response<T>> typeReference) {
        return apiClient
            .get()
            .uri(uriBuilder ->
                uriBuilder
                    .path("/LineData.json")
                    .queryParam("key", apiKey)
                    .queryParam("model", model)
                    .queryParam("DefaultTransportModeCode", "BUS")
                    .build()
            )
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .onStatus(
                HttpStatus::isError,
                response -> response.bodyToMono(String.class).flatMap(error -> Mono.error(new NoDataException(error)))
            )
            .bodyToMono(typeReference)
            .map(Response::getData)
            .map(ResponseData::getItems)
            .onErrorMap(
                Predicate.not(NoDataException.class::isInstance),
                throwable -> new NoDataException("Something went wrong")
            )
            .doOnError(throwable -> {
                logger.error("Could not parse bus data", throwable);
            });
    }

    private static class Response<T> {

        @JsonProperty("ResponseData")
        private ResponseData<T> data;

        public ResponseData<T> getData() {
            return data;
        }
    }

    private static class ResponseData<T> {

        @JsonProperty("Result")
        private List<T> items;

        public List<T> getItems() {
            return items;
        }
    }
}
