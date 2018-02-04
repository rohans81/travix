package com.travix.medusa.busyflights.domain.busyflights;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class BusyFlightsResponse {
    private String airline;
    private String supplier;
    private double fare;
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;
    private String arrivalDate;
}
