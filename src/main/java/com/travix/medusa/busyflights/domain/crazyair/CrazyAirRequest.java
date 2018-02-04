package com.travix.medusa.busyflights.domain.crazyair;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CrazyAirRequest {
    private String origin;
    private String destination;
    private String departureDate;
    private String returnDate;
    private int passengerCount;
}
