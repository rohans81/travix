package com.travix.medusa.busyflights.supplier;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import java.util.List;

/**
 * Interface for all flight suppliers
 */
public interface FlightSupplier {
    List<BusyFlightsResponse> searchFlights(BusyFlightsRequest request);
}
