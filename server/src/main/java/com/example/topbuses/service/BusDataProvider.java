package com.example.topbuses.service;

import com.example.topbuses.model.BusJourneyObject;
import com.example.topbuses.model.BusStopObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BusDataProvider {

    private final WebClient apiClient;

    public BusDataProvider(WebClient apiClient) {
        this.apiClient = apiClient;
    }

    public Mono<List<BusStopObject>> getBusStops() {
        ParameterizedTypeReference<Response<BusStopObject>> typeReference = new ParameterizedTypeReference<>() {};
        return fetch("stops", typeReference);
    }

    public Mono<List<BusJourneyObject>> getBusJourneys() {
        ParameterizedTypeReference<Response<BusJourneyObject>> typeReference = new ParameterizedTypeReference<>() {};
        return fetch("journeys", typeReference);
    }

    private <T> Mono<List<T>> fetch(String uri, ParameterizedTypeReference<Response<T>> typeReference) {
        return apiClient
            .get()
            .uri(uri)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            // .bodyToMono(String.class)
            .bodyToMono(typeReference)
            .map(Response::getData)
            .map(ResponseData::getItems);
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
