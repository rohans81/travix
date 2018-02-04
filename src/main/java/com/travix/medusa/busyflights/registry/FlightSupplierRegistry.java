package com.travix.medusa.busyflights.registry;

import com.travix.medusa.busyflights.supplier.FlightSupplier;
import com.travix.medusa.busyflights.supplier.crazyair.CrazyAirSupplier;
import com.travix.medusa.busyflights.supplier.toughjet.ToughJetSupplier;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightSupplierRegistry {
    @Autowired
    CrazyAirSupplier crazyAirSupplier;

    @Autowired
    ToughJetSupplier toughJetSupplier;

    public List<FlightSupplier> getFlightSuppliers() {
        return Arrays.asList(crazyAirSupplier, toughJetSupplier);
    }

}
