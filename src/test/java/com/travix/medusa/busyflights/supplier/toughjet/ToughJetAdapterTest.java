package com.travix.medusa.busyflights.supplier.toughjet;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.util.RequestResponseGenerators;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class ToughJetAdapterTest {

    ToughJetAdapter toughJetAdapter = new ToughJetAdapter();

    @Test
    public void testConvertRequest() {
        final BusyFlightsRequest busyFlightsRequest = RequestResponseGenerators
            .getRandomBusyFlightsRequest();
        final ToughJetRequest toughJetRequest = toughJetAdapter
            .convertRequest(busyFlightsRequest);

        //TODO : check each field of both requests

        System.out.println(busyFlightsRequest);
        System.out.println(toughJetRequest);
    }

    @Test
    public void testConvertResponse() {
        final BusyFlightsRequest busyFlightsRequest = RequestResponseGenerators
            .getRandomBusyFlightsRequest();

        final List<ToughJetResponse> response = RequestResponseGenerators
            .getRandomToughJetResponseList(toughJetAdapter.convertRequest(busyFlightsRequest));

        final List<BusyFlightsResponse> busyFlightResponse = toughJetAdapter
            .convertResponse(response);
        Assert.assertEquals(response.size(), busyFlightResponse.size());
        //TODO: check each field of both responses
        System.out.println(response);
        System.out.println(busyFlightResponse);
    }
}
