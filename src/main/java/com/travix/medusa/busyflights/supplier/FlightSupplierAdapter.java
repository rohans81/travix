package com.travix.medusa.busyflights.supplier;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import java.util.List;

public interface FlightSupplierAdapter<I, O> {

    I convertRequest(BusyFlightsRequest request);

    List<BusyFlightsResponse> convertResponse(List<O> response);
}
