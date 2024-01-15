package com.transferz.service.flight;

import com.transferz.service.dto.flight.FlightDto;

public interface FlightService {

    String createFlight();

    FlightDto chooseFlightForPassenger();

    int departFlight(String flightCode);
}
