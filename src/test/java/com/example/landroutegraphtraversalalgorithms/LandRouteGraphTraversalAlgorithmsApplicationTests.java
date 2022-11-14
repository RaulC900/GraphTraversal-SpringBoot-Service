package com.example.landroutegraphtraversalalgorithms;

import com.example.landroutegraphtraversalalgorithms.controller.RoutingController;
import com.example.landroutegraphtraversalalgorithms.model.Graph;
import com.example.landroutegraphtraversalalgorithms.service.RoutingService;
import lombok.Data;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoutingController.class)
@Data
class LandRouteGraphTraversalAlgorithmsApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RoutingService routingService;

    @PostConstruct
    public void init() throws IOException {
        routingService = new RoutingService();
    }

    @Test
    void contextLoads() throws Exception {
        //routingService = new RoutingService();
        Boolean b = routingService.testGraphHasCountry("NZL");
        assertThat(b).isTrue();

        //this.mockMvc.perform(get("/routing/{countryCode}", "NZL")).andExpect(status().isOk());
    }

}
