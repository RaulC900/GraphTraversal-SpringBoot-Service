package com.example.landroutegraphtraversalalgorithms.controller;

import com.example.landroutegraphtraversalalgorithms.service.RoutingService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@Data
@RequestMapping("/routing")
public class RoutingController {

    private final RoutingService routingService;

    @GetMapping(path = "/{origin}/{destination}")
    public ResponseEntity<Map<String, LinkedList<String>>> getShortestRoute
            (
                    @PathVariable String origin,
                    @PathVariable String destination
            ) {

        LinkedList<String> shortestRoute = routingService.getShortestRoute(origin, destination);
        if(shortestRoute == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Map<String, LinkedList<String>> result = new HashMap<>();
        result.put("route", shortestRoute);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
