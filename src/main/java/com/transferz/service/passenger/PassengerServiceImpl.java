package com.transferz.service.passenger;

import com.transferz.dao.Passenger;
import com.transferz.repository.passenger.PassengerRepository;
import com.transferz.service.dto.passenger.PassengerCreateDto;
import com.transferz.service.flight.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    @Value("${max-flight-capacity}")
    private int maxFlightCapacity;
    private final FlightService flightService;
    private final PassengerRepository passengerRepository;

    @Override
    @Transactional
    public void createPassenger(PassengerCreateDto createDto) {
        val nearestFlight = flightService.chooseFlightForPassenger();
        if (nearestFlight == null) {//2
            val flightCode = flightService.createFlight();//1
            passengerRepository.createPassenger(
                    passenger(
                            createDto,
                            flightCode
                    )
            );
        } else {
            passengerRepository.createPassenger(passenger(
                    createDto,
                    nearestFlight.getCode()
            ));
            if (flightShouldDepart(nearestFlight.getCode())) {
                if (flightService.departFlight(nearestFlight.getCode()) == 0)
                    throw new IllegalArgumentException(
                            "Flight has been departed during registration, please try again."
                    );
            }
        }
    }

    private boolean flightShouldDepart(String flightCode) {
        return countPassengersPerFlight(flightCode) >= maxFlightCapacity;
    }

    @Override
    public int countPassengersPerFlight(String flightCode) {
        return passengerRepository.countPassengersPerFlight(flightCode);
    }

    private Passenger passenger(
            PassengerCreateDto createDto,
            String flightCode
    ) {
        return new Passenger(
                createDto.getName(),
                flightCode
        );
    }
}
