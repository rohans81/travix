package com.travix.medusa.busyflights.supplier.crazyair;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.supplier.FlightSupplierAdapter;
import com.travix.medusa.busyflights.util.DateUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CrazyAirAdapter implements FlightSupplierAdapter<CrazyAirRequest,CrazyAirResponse> {


    @Override
    public CrazyAirRequest convertRequest(final BusyFlightsRequest request) {
        return CrazyAirRequest.builder()
                              .origin(request.getOrigin())
                              .destination(request.getDestination())
                              .departureDate(request.getDepartureDate())
                              .returnDate(request.getReturnDate())
                              .passengerCount(request.getNumberOfPassengers())
                              .build();
    }

    @Override
    public List<BusyFlightsResponse> convertResponse(final List<CrazyAirResponse> response) {
        return response.stream()
                       .map(c -> BusyFlightsResponse.builder()
                                                    .supplier(CrazyAirSupplier.SUPPLIER_NAME)
                                                    .airline(c.getAirline())
                                                    .departureAirportCode(c.getDepartureAirportCode())
                                                    .destinationAirportCode(c.getDestinationAirportCode())
                                                    .departureDate(DateUtil.convertFormat(c.getDepartureDate(),
                                                                                          ISO_LOCAL_DATE_TIME,
                                                                                          ISO_DATE_TIME))
                                                    .arrivalDate(DateUtil.convertFormat(c.getArrivalDate(),
                                                                                        ISO_LOCAL_DATE_TIME,
                                                                                        ISO_DATE_TIME))
                                                    .fare(c.getPrice())
                                                    .build())
                       .collect(Collectors.toList());
    }

}
