package com.travix.medusa.busyflights.supplier.toughjet;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.supplier.FlightSupplierAdapter;
import com.travix.medusa.busyflights.supplier.FlightSupplierBase;
import com.travix.medusa.busyflights.supplier.FlightSupplierClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToughJetSupplier implements FlightSupplierBase<ToughJetRequest, ToughJetResponse> {

    public static final String SUPPLIER_NAME = "ToughJet";

    @Autowired
    ToughJetClient toughJetClient;

    @Autowired
    ToughJetAdapter toughJetAdapter;


    @Override
    public FlightSupplierAdapter<ToughJetRequest, ToughJetResponse> getFlightSupplierAdapter() {
        return toughJetAdapter;
    }

    @Override
    public FlightSupplierClient<ToughJetRequest, ToughJetResponse> getFlightSupplierClient() {
        return toughJetClient;
    }
}
