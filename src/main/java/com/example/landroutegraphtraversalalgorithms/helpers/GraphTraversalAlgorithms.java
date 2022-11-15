package com.example.landroutegraphtraversalalgorithms.helpers;

import com.example.landroutegraphtraversalalgorithms.model.Graph;
import com.example.landroutegraphtraversalalgorithms.service.RoutingService;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.*;

public class GraphTraversalAlgorithms {

    public static LinkedList<String> findShortestPath(Map<String, LinkedList<String>> adjVertices, String origin, String destination) {

        Map<String, String> pred = new HashMap<>(adjVertices.size());
        Map<String, Integer> dist = new HashMap<>(adjVertices.size());

        if(BFS(adjVertices, origin, destination, pred, dist) == false) {
            return null;
        }

        LinkedList<String> path = new LinkedList<>();
        String aux = destination;
        path.add(aux);
        while (pred.get(aux) != null) {
            path.add(pred.get(aux));
            aux = pred.get(aux);
        }

        return path;
    }

    private static Boolean BFS(Map<String, LinkedList<String>> adjVertices, String origin, String destination, Map<String, String> pred, Map<String, Integer> dist) {
        LinkedList<String> queue = new LinkedList<>();

        Map<String, Boolean> visited = new HashMap<>(adjVertices.size());

        for(String key : adjVertices.keySet()) {
            visited.put(key, false);
            dist.put(key, Integer.MAX_VALUE);
            pred.put(key, null);
        }

        visited.put(origin, true);
        dist.put(origin, 0);
        queue.add(origin);

        while (!queue.isEmpty()) {
            String u = queue.remove();
            for (String country : adjVertices.get(u)) {
                if(visited.get(country) == false) {
                    visited.put(country, true);
                    dist.put(country, dist.get(u) + 1);
                    pred.put(country, u);
                    queue.add(country);

                    if (country.equals(destination)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
