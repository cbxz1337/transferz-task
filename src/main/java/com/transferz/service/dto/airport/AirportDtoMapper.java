package com.transferz.service.dto.airport;

import com.transferz.dao.Airport;

public class AirportDtoMapper {

    public static AirportDto airportDto(Airport airport) {
        return new AirportDto(
                airport.getName(),
                airport.getCode(),
                airport.getCountry()
        );
    }
}
