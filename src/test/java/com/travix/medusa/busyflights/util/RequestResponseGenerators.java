package com.travix.medusa.busyflights.util;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.supplier.crazyair.CrazyAirSupplier;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RequestResponseGenerators {

    public static BusyFlightsRequest getRandomBusyFlightsRequest() {
        return BusyFlightsRequest
            .builder()
            .departureDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE))
            .returnDate(LocalDateTime.now().plusDays(10).format(DateTimeFormatter.ISO_LOCAL_DATE))
            .numberOfPassengers(2)
            .origin("AMS")
            .destination("BOM")
            .build();
    }
    public static List<CrazyAirResponse> getRandomCrazyAirResponseList(CrazyAirRequest request) {
            int resultSize = 3;
            List<CrazyAirResponse> result = new ArrayList<>();
            for (int i=0; i<resultSize; i++) {
                result.add(getRandomCrazyAirResponse(request));
            }
            return result;
    }

    private static CrazyAirResponse getRandomCrazyAirResponse(CrazyAirRequest request){
        return CrazyAirResponse.builder()
                               .departureDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))

            .arrivalDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            .destinationAirportCode(request.getDestination())
            .departureAirportCode(request.getOrigin())
            .airline(CrazyAirSupplier.SUPPLIER_NAME)
            .cabinclass("Business")
            .price(new Random().nextDouble())
            .build();
    }


    public static List<ToughJetResponse> getRandomToughJetResponseList(ToughJetRequest request) {
        int resultSize = 3;
        List<ToughJetResponse> result = new ArrayList<>();
        for (int i=0; i<resultSize; i++) {
            result.add(getRandomToughJetResponse(request));
        }
        return result;
    }

    private static ToughJetResponse getRandomToughJetResponse(ToughJetRequest request){
        return ToughJetResponse.builder()
                               .outboundDateTime(Instant.now().toString())

                               .inboundDateTime(Instant.now().toString())
                               .arrivalAirportName(request.getTo())
                               .departureAirportName(request.getFrom())
                               .carrier("KLM")
                               .basePrice(new Random().nextDouble())
                               .discount(10)
                               .tax(1.0)
                               .build();
    }
    public static List<BusyFlightsResponse> getRandomBusyFlightsResponseList(BusyFlightsRequest busyFlightsRequest) {
        int resultSize = 3;
        List<BusyFlightsResponse> result = new ArrayList<>();
        for (int i=0; i<resultSize; i++) {
            result.add(getRandomBusyFlightsResponse(busyFlightsRequest));
        }
        return result;

    }

    public static BusyFlightsResponse getRandomBusyFlightsResponse(BusyFlightsRequest request){
        return BusyFlightsResponse.builder()
                                  .fare(new Random().nextDouble())
                                  .airline("KLM")
                                  .supplier(CrazyAirSupplier.SUPPLIER_NAME)
                                  .arrivalDate(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                                  .departureDate(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                                  .departureAirportCode(request.getOrigin())
                                  .destinationAirportCode(request.getDestination())
                                  .build();
    }



    }
