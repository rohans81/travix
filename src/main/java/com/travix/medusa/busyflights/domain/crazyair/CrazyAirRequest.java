package com.travix.medusa.busyflights.domain.crazyair;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
public class CrazyAirRequest {
    private String origin;
    private String destination;
    private String departureDate;
    private String returnDate;
    private int passengerCount;
}
