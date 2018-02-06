package com.travix.medusa.busyflights.supplier;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class has common strategy for all flight suppliers
 * @param <I> Request type of flight supplier
 * @param <O> Response type of flight supplier
 */
public interface FlightSupplierStrategy<I,O> extends FlightSupplier{
    FlightSupplierAdapter<I,O> getFlightSupplierAdapter();
    FlightSupplierClient<I,O> getFlightSupplierClient();

    default  List<BusyFlightsResponse> searchFlights(BusyFlightsRequest busyFlightsRequest){
        final I request = getFlightSupplierAdapter().convertRequest(busyFlightsRequest);
        final List<O> response = getFlightSupplierClient().searchFlights(request);
        return getFlightSupplierAdapter().convertResponse(response)
                                         .stream()
                                         .map(FlightSupplierStrategy::roundFare)
                                         .collect(Collectors.toList());
    }

    /**
     * Rounds fare to 2 decimal places
     * @param response
     * @return
     */
    static BusyFlightsResponse roundFare ( BusyFlightsResponse response) {
        return response.builder().fare(Math.round(response.getFare()*100.0)/100.0).build();
    }
}
