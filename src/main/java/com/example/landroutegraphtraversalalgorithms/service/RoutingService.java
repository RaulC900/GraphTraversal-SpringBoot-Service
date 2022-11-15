package com.example.landroutegraphtraversalalgorithms.service;

import com.example.landroutegraphtraversalalgorithms.helpers.GraphTraversalAlgorithms;
import com.example.landroutegraphtraversalalgorithms.model.Country;
import com.example.landroutegraphtraversalalgorithms.model.Graph;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Data
public class RoutingService {

    private final Graph countriesGraph;

    public RoutingService() throws IOException {
        this.countriesGraph = new Graph();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //read JSON file and convert to a customer object
        List<Country> countriesList = objectMapper.readValue
                (
                    getClass().getResourceAsStream("/countries.json"),
                    new TypeReference<ArrayList<Country>>() {}
                );

        initializeGraph(countriesList);
    }

    public Boolean isAdjVerticesNull() {
        return this.countriesGraph.getAdjVertices().isEmpty();
    }

    public Graph getGraph() {
        return this.countriesGraph;
    }

    public void initializeGraph(List<Country> countriesList) {
        for(Country country : countriesList) {
            String countryCode = country.getCca3();
            this.countriesGraph.addVertex(countryCode);
            for(String borderCountryCode: country.getBorders()) {
                this.countriesGraph.addEdge(countryCode, borderCountryCode);
            }
        }
    }

    public Boolean testGraphHasCountry(String countryCode) {
        if(countriesGraph == null) {
            return false;
        }

        if(countriesGraph.getAdjVertices() == null) {
            return false;
        }

        return countriesGraph.getAdjVertices().containsKey(countryCode);
    }

    public LinkedList<String> getShortestRoute(String origin, String destination) {

        LinkedList<String> shortestPath = GraphTraversalAlgorithms.findShortestPath(countriesGraph.getAdjVertices(), origin, destination);
        if(shortestPath == null) {
            return null;
        }
        LinkedList<String> resultingPath = new LinkedList<>();
        shortestPath.stream().forEach(c -> resultingPath.add(c));
        return resultingPath;
    }
}
