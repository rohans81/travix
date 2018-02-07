package com.travix.medusa.busyflights.supplier.crazyair;

import com.codahale.metrics.annotation.Timed;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.supplier.FlightSupplierClient;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * This class has http client code to call crazy air service
 */
@Service
public class CrazyAirClient implements FlightSupplierClient<CrazyAirRequest,CrazyAirResponse> {

    @Override
    public List<CrazyAirResponse> searchFlights(final CrazyAirRequest request) {
        return null;
    }
}
