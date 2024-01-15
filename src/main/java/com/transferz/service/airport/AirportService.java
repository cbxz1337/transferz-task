package com.transferz.service.airport;

import com.transferz.service.dto.airport.AirportDto;
import com.transferz.service.dto.airport.AirportPaginatedDto;
import com.transferz.service.dto.airport.AirportSearchDto;

public interface AirportService {

    void createAirport(AirportDto airportDto);

    AirportPaginatedDto getPaginatedAirports(AirportSearchDto filterDto);

    AirportDto getAvailableAirport();
}
