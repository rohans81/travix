package com.travix.medusa.busyflights.supplier.toughjet;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.supplier.FlightSupplierClient;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ToughJetClient implements FlightSupplierClient<ToughJetRequest, ToughJetResponse> {

    @Override
    public List<ToughJetResponse> searchFlights(final ToughJetRequest request) {
        return null;
    }
}
