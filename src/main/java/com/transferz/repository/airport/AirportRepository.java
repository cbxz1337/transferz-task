package com.transferz.repository.airport;

import com.transferz.dao.Airport;
import com.transferz.service.dto.airport.AirportSearchDto;

import java.util.List;

public interface AirportRepository {

    List<Airport> getPaginatedAirportResult(AirportSearchDto searchDto);

    Airport getAvailableAirport();

    void createAirport(Airport airport);
}
