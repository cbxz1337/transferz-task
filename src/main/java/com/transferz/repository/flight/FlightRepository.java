package com.transferz.repository.flight;

import com.transferz.dao.Flight;

public interface FlightRepository {

    Flight nextFlight();

    String createFlight(String destAirport);

    int updateFlightDepartureInfo(Flight flight);
}
