package com.travix.medusa.busyflights.service;

import static org.mockito.Matchers.any;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.registry.FlightSupplierRegistry;
import com.travix.medusa.busyflights.supplier.crazyair.CrazyAirAdapter;
import com.travix.medusa.busyflights.supplier.crazyair.CrazyAirClient;
import com.travix.medusa.busyflights.supplier.crazyair.CrazyAirSupplier;
import com.travix.medusa.busyflights.supplier.toughjet.ToughJetAdapter;
import com.travix.medusa.busyflights.supplier.toughjet.ToughJetClient;
import com.travix.medusa.busyflights.supplier.toughjet.ToughJetSupplier;
import com.travix.medusa.busyflights.util.RequestResponseGenerators;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {BusyFlightsService.class,
                                 CrazyAirAdapter.class,
                                 FlightSupplierRegistry.class,
                                 CrazyAirSupplier.class,
                                 ToughJetAdapter.class,
                                 ToughJetSupplier.class,
})

@RunWith(SpringJUnit4ClassRunner.class)
public class BusyFlightsServiceTest {

    @Autowired
    BusyFlightsService busyFlightsService;

    @Autowired
    CrazyAirAdapter crazyAirAdapter;

    @Autowired
    CrazyAirSupplier crazyAirSupplier;

    @MockBean
    CrazyAirClient mockCrazyAirClient;


    @Autowired
    ToughJetAdapter toughJetAdapter;

    @Autowired
    ToughJetSupplier toughJetSupplier;

    @MockBean
    ToughJetClient mockToughJetClient;

    @MockBean
    FlightSupplierRegistry flightSupplierRegistry;

    @Test
    public void testOnlyCrazyAirSupplier() {

        Mockito.when(flightSupplierRegistry.getFlightSuppliers()).thenReturn(Arrays.asList(crazyAirSupplier));

        BusyFlightsRequest busyFlightsRequest = RequestResponseGenerators.getRandomBusyFlightsRequest();
        final List<CrazyAirResponse> crazyAirResponses = mockCrazyAirSupplier(busyFlightsRequest);

        final List<BusyFlightsResponse> busyFlightsResponse = busyFlightsService
            .searchFlights(busyFlightsRequest);

        Assert.assertEquals(crazyAirResponses.size(), busyFlightsResponse.size());
        //check prices are sorted
        final List<Double> fares = busyFlightsResponse.stream().map(BusyFlightsResponse::getFare)
                                                      .collect(Collectors.toList());
        Assert.assertEquals(fares.stream().sorted().collect(Collectors.toList()), fares);

    }

    @Test
    public void testErrorInOnlyClient() {
        Mockito.when(flightSupplierRegistry.getFlightSuppliers()).thenReturn(Arrays.asList(crazyAirSupplier));

        BusyFlightsRequest busyFlightsRequest = RequestResponseGenerators.getRandomBusyFlightsRequest();
        Mockito.when(mockCrazyAirClient.searchFlights(any(CrazyAirRequest.class)))
               .thenThrow(new RuntimeException("Error"));
        final List<BusyFlightsResponse> busyFlightsResponse = busyFlightsService
            .searchFlights(busyFlightsRequest);

        Assert.assertTrue(busyFlightsResponse.isEmpty());

    }


    @Test
    public void testOnlyToughJetSupplier() {

        Mockito.when(flightSupplierRegistry.getFlightSuppliers()).thenReturn(Arrays.asList(toughJetSupplier));

        BusyFlightsRequest busyFlightsRequest = RequestResponseGenerators.getRandomBusyFlightsRequest();
        final List<ToughJetResponse> toughJetResponses = mockToughJetSupplier(busyFlightsRequest);

        final List<BusyFlightsResponse> busyFlightsResponse = busyFlightsService
            .searchFlights(busyFlightsRequest);

        Assert.assertEquals(toughJetResponses.size(), busyFlightsResponse.size());
        //check prices are sorted
        final List<Double> fares = busyFlightsResponse.stream().map(BusyFlightsResponse::getFare)
                                                      .collect(Collectors.toList());
        Assert.assertEquals(fares.stream().sorted().collect(Collectors.toList()), fares);

    }

    @Test
    public void testAllSuppliers(){
        Mockito.when(flightSupplierRegistry.getFlightSuppliers()).thenReturn(Arrays.asList(toughJetSupplier,crazyAirSupplier));
        BusyFlightsRequest busyFlightsRequest = RequestResponseGenerators.getRandomBusyFlightsRequest();

        final List<ToughJetResponse> toughJetResponses = mockToughJetSupplier(busyFlightsRequest);

        final List<CrazyAirResponse> crazyAirResponses = mockCrazyAirSupplier(busyFlightsRequest);

        final List<BusyFlightsResponse> busyFlightsResponse = busyFlightsService
            .searchFlights(busyFlightsRequest);

        Assert.assertEquals(toughJetResponses.size()+crazyAirResponses.size(), busyFlightsResponse.size());
        //check prices are sorted
        final List<Double> fares = busyFlightsResponse.stream().map(BusyFlightsResponse::getFare)
                                                      .collect(Collectors.toList());
        Assert.assertEquals(fares.stream().sorted().collect(Collectors.toList()), fares);

    }

    @Test
    public void testErrorInOneAmongManySuppliers(){
        Mockito.when(flightSupplierRegistry.getFlightSuppliers()).thenReturn(Arrays.asList(toughJetSupplier,crazyAirSupplier));

        BusyFlightsRequest busyFlightsRequest = RequestResponseGenerators.getRandomBusyFlightsRequest();
        Mockito.when(mockCrazyAirClient.searchFlights(any(CrazyAirRequest.class)))
               .thenThrow(new RuntimeException("Error"));

        final List<ToughJetResponse> toughJetResponses = mockToughJetSupplier(busyFlightsRequest);

        final List<BusyFlightsResponse> busyFlightsResponse = busyFlightsService
            .searchFlights(busyFlightsRequest);
        Assert.assertEquals(toughJetResponses.size(), busyFlightsResponse.size());

    }

    private final List<ToughJetResponse> mockToughJetSupplier(BusyFlightsRequest busyFlightsRequest){
        final List<ToughJetResponse> toughJetResponses = RequestResponseGenerators
            .getRandomToughJetResponseList(toughJetAdapter.convertRequest(busyFlightsRequest));

        Mockito.when(mockToughJetClient.searchFlights(any(ToughJetRequest.class)))
               .thenReturn(toughJetResponses);
        return  toughJetResponses;

    }

    private final List<CrazyAirResponse> mockCrazyAirSupplier(BusyFlightsRequest busyFlightsRequest){
        final List<CrazyAirResponse> crazyAirResponses = RequestResponseGenerators
            .getRandomCrazyAirResponseList(crazyAirAdapter.convertRequest(busyFlightsRequest));

        Mockito.when(mockCrazyAirClient.searchFlights(any(CrazyAirRequest.class)))
               .thenReturn(crazyAirResponses);
        return  crazyAirResponses;

    }

}
