package com.travix.medusa.busyflights.domain.busyflights;

import com.travix.medusa.busyflights.validation.DateTime;
import com.travix.medusa.busyflights.validation.DateTimeFormat;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
public class BusyFlightsRequest {

    @Size(min = 3, max = 3)
    private String origin;
    @Size(min = 3, max = 3)
    private String destination;
    @DateTime(format = DateTimeFormat.ISO_LOCAL_DATE)
    private String departureDate;
    @DateTime(format = DateTimeFormat.ISO_LOCAL_DATE)
    private String returnDate;
    @Range(min = 1, max = 10)
    private int numberOfPassengers;
}
