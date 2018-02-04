package com.travix.medusa.busyflights.supplier;

import java.util.List;

public interface FlightSupplierClient<I,O> {
    List<O> searchFlights(I request);

}
