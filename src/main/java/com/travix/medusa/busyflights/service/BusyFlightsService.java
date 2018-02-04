package com.travix.medusa.busyflights.service;

import com.codahale.metrics.annotation.Timed;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.registry.FlightSupplierRegistry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BusyFlightsService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FlightSupplierRegistry flightSupplierRegistry;

    @Value("${executors.thread.count:25}")
    private int maxThreads;

    private ExecutorService executorService;

    @PostConstruct
    public void init() {
        executorService = Executors.newFixedThreadPool(maxThreads);
    }

    @Timed
    public List<BusyFlightsResponse> searchFlights(BusyFlightsRequest request) {
        log.trace("Starting search request for {}", request);
        return flightSupplierRegistry.getFlightSuppliers()
                                     .stream()
                                     .map(supplier -> executorService.submit(() -> supplier.searchFlights(request)))
                                     .flatMap(l -> getFutureResult(l).stream())
                                     .sorted(Comparator.comparingDouble(BusyFlightsResponse::getFare))
                                     .collect(Collectors.toList());
    }

    /**
     * Sync call to fetch supplier result. Return empty list in case of  error
     * @param future
     * @return
     */
    private List<BusyFlightsResponse> getFutureResult(Future<List<BusyFlightsResponse>> future) {
        try {
            return future.get();
        } catch (InterruptedException|ExecutionException e) {
            log.error("Error fetching response from supplier {}", e);
            return new ArrayList();
        }
    }

}
