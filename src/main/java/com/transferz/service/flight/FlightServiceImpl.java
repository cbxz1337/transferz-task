package com.transferz.service.flight;

import com.transferz.dao.Flight;
import com.transferz.repository.flight.FlightRepository;
import com.transferz.service.airport.AirportService;
import com.transferz.service.dto.flight.FlightDto;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final AirportService airportService;
    private final FlightRepository flightRepository;


    @Override
    public String createFlight() {
        val airport = airportService.getAvailableAirport();
        return flightRepository.createFlight(airport.getCode());
    }

    @Override
    public FlightDto chooseFlightForPassenger() {
        val flightModel = getNextFlight();
        if (flightModel == null) return null;
        return new FlightDto(
                flightModel.getCode(),
                flightModel.getDestinationAirportId(),
                flightModel.getDepartureTime()
        );
    }

    @Override
    public int departFlight(String flightCode) {
        return flightRepository.updateFlightDepartureInfo(
                departFlightModel(flightCode)
        );
    }

    private Flight getNextFlight() {
        try {
            return flightRepository.nextFlight();
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private Flight departFlightModel(
            String flightCode) {
        return new Flight(
                flightCode,
                null,
                LocalDateTime.now()
        );
    }
}
