package com.example.landroutegraphtraversalalgorithms.controller;

import com.example.landroutegraphtraversalalgorithms.service.RoutingService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Data
@RequestMapping("/routing")
public class RoutingController {

    private final RoutingService routingService;

    @GetMapping(path = "/{countryCode}")
    public ResponseEntity<Map<String, List<String>>> getShortestRoute
            (
                    @PathVariable String countryCode
            ) {
//        if(routingService.testGraphHasCountry(countryCode))
//            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/{origin}/{destination}")
    public ResponseEntity<Map<String, List<String>>> getShortestRoute
            (
                    @PathVariable String origin,
                    @PathVariable String destination
            ) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
