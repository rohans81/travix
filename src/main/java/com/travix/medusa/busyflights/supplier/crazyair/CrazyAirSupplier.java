package com.travix.medusa.busyflights.supplier.crazyair;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.supplier.FlightSupplierAdapter;
import com.travix.medusa.busyflights.supplier.FlightSupplierStrategy;
import com.travix.medusa.busyflights.supplier.FlightSupplierClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrazyAirSupplier implements FlightSupplierStrategy<CrazyAirRequest, CrazyAirResponse> {

    public static final String SUPPLIER_NAME = "CrazyAir";

    @Autowired
    CrazyAirClient crazyAirClient;

    @Autowired
    CrazyAirAdapter crazyAirAdapter;

    @Override
    public FlightSupplierAdapter<CrazyAirRequest, CrazyAirResponse> getFlightSupplierAdapter() {
        return crazyAirAdapter;
    }

    @Override
    public FlightSupplierClient<CrazyAirRequest, CrazyAirResponse> getFlightSupplierClient() {
        return crazyAirClient;
    }

}
