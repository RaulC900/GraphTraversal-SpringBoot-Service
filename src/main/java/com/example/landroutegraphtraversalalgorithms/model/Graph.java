package com.example.landroutegraphtraversalalgorithms.model;

import lombok.Data;

import java.util.*;

@Data
public class Graph {

    private Map<String, LinkedList<String>> adjVertices;

    public Graph() {
        this.adjVertices = new HashMap<>();
    }

    public void addVertex(String code) {
        adjVertices.putIfAbsent(code, new LinkedList<>());
    }

    public void addEdge(String code1, String code2) {
        adjVertices.get(code1).add(code2);
    }
}
