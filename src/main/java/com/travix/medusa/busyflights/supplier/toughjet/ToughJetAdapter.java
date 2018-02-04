package com.travix.medusa.busyflights.supplier.toughjet;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.supplier.FlightSupplierAdapter;
import com.travix.medusa.busyflights.util.DateUtil;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import static java.time.format.DateTimeFormatter.*;

@Component
public class ToughJetAdapter implements FlightSupplierAdapter<ToughJetRequest, ToughJetResponse> {

    @Override
    public ToughJetRequest convertRequest(final BusyFlightsRequest request) {
        return ToughJetRequest.builder()
                              .from(request.getOrigin())
                              .to(request.getDestination())
                              .numberOfAdults(request.getNumberOfPassengers())
                              .inboundDate(request.getDepartureDate())
                              .outboundDate(request.getReturnDate())
                              .build();
    }

    @Override
    public List<BusyFlightsResponse> convertResponse(final List<ToughJetResponse> response) {
        return response.stream()
                       .map(r -> BusyFlightsResponse.builder()
                                                    .departureAirportCode(r.getDepartureAirportName())
                                                    .destinationAirportCode(r.getArrivalAirportName())
                                                    .airline(r.getCarrier())
                                                    .fare(r.getBasePrice()*(1-r.getDiscount()/100)+r.getTax())
                                                    .supplier(ToughJetSupplier.SUPPLIER_NAME)
                                                    .departureDate(DateUtil.convertFormat(r.getOutboundDateTime(), ISO_INSTANT.withZone(
                                                        ZoneId.systemDefault()), ISO_DATE_TIME))
                                                    .arrivalDate(DateUtil.convertFormat(r.getInboundDateTime(), ISO_INSTANT.withZone(ZoneId.systemDefault()), ISO_DATE_TIME))
                                                    .build())
            .collect(Collectors.toList());
    }
}
