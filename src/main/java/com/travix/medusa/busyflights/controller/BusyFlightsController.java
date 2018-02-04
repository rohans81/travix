package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightsService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BusyFlightsController {

    @Autowired
    private BusyFlightsService busyFlightsService;

    @RequestMapping(value = "/searchFlights", method = RequestMethod.POST, produces = {"application/json"})
    public
    @ResponseBody
    List<BusyFlightsResponse> searchAirFare(@Valid @RequestBody BusyFlightsRequest request) {
        return busyFlightsService.searchFlights(request);
    }
}
