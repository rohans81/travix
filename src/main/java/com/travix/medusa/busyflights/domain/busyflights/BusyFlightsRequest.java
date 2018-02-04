package com.travix.medusa.busyflights.domain.busyflights;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class BusyFlightsRequest {
    private String origin;
    private String destination;
    private String departureDate;
    private String returnDate;
    private int numberOfPassengers;
}
