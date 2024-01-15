package com.transferz.repository.passenger;

import com.transferz.dao.Passenger;

public interface PassengerRepository {

    void createPassenger(Passenger passenger);

    int countPassengersPerFlight(String flightCode);
}
