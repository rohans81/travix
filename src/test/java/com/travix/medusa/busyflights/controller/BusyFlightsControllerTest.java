package com.travix.medusa.busyflights.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightsService;
import com.travix.medusa.busyflights.util.RequestResponseGenerators;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(BusyFlightsController.class)
public class BusyFlightsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BusyFlightsService busyFlightsService;

    @Test
    public void testSearchFlights() throws Exception {
        BusyFlightsRequest busyFlightsRequest = RequestResponseGenerators.getRandomBusyFlightsRequest();
        List<BusyFlightsResponse> busyFlightsResponses = RequestResponseGenerators
            .getRandomBusyFlightsResponseList(busyFlightsRequest);

        given(busyFlightsService.searchFlights(busyFlightsRequest)).willReturn(busyFlightsResponses);

        Gson gson = new GsonBuilder().create();
        mvc.perform(post("/searchFlights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(busyFlightsRequest)))
           .andDo(print())
           .andExpect(status().is2xxSuccessful())
           .andExpect(content().string(gson.toJson(busyFlightsResponses)));
    }
}
