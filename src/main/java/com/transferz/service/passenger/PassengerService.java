package com.transferz.service.passenger;

import com.transferz.service.dto.passenger.PassengerCreateDto;

public interface PassengerService {

    void createPassenger(PassengerCreateDto createDto);

    int countPassengersPerFlight(String flightCode);
}
