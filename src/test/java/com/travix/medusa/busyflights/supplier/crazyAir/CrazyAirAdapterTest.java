package com.travix.medusa.busyflights.supplier.crazyAir;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.supplier.crazyair.CrazyAirAdapter;
import com.travix.medusa.busyflights.util.RequestResponseGenerators;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class CrazyAirAdapterTest {

    CrazyAirAdapter crazyAirAdapter = new CrazyAirAdapter();
    @Test
    public void testConvertRequest() {
        final BusyFlightsRequest busyFlightsRequest = RequestResponseGenerators
            .getRandomBusyFlightsRequest();
        final CrazyAirRequest crazyAirRequest = crazyAirAdapter
            .convertRequest(busyFlightsRequest);

        //TODO : check each field of both requests

        System.out.println(busyFlightsRequest);
        System.out.println(crazyAirRequest);
    }

    @Test
    public void testConvertResponse() {
        final BusyFlightsRequest busyFlightsRequest = RequestResponseGenerators
            .getRandomBusyFlightsRequest();

        final List<CrazyAirResponse> response = RequestResponseGenerators
            .getRandomCrazyAirResponseList(crazyAirAdapter.convertRequest(busyFlightsRequest));

        final List<BusyFlightsResponse> busyFlightResponse = crazyAirAdapter
            .convertResponse(response);
        Assert.assertEquals(response.size(), busyFlightResponse.size());
        //TODO: check each field of both responses
        System.out.println(response);
        System.out.println(busyFlightResponse);
    }
}
