package com.transferz.controller.airport.model;

import com.transferz.controller.airport.model.request.AirportSearchRequest;
import com.transferz.controller.airport.model.request.CreateAirportRequest;
import com.transferz.controller.airport.model.response.AirportPaginatedResponse;
import com.transferz.controller.airport.model.response.AirportResponse;
import com.transferz.service.dto.airport.*;

import java.util.stream.Collectors;

public class AirportRequestMapper {

    public static AirportResponse toAirportResponse(AirportDto airportDto) {
        return new AirportResponse(
                airportDto.getName(),
                airportDto.getCode(),
                airportDto.getCountry()
        );
    }

    public static AirportDto toAirportDto(CreateAirportRequest createAirportRequest) {
        return new AirportDto(
                createAirportRequest.getName(),
                createAirportRequest.getCode(),
                createAirportRequest.getCountry()
        );
    }

    public static AirportSearchDto toAirportSearchDto(AirportSearchRequest airportSearchRequest) {
        return new AirportSearchDto(
                new AirportFilterDto(
                        airportSearchRequest.getFilterRequest().getCode(),
                        airportSearchRequest.getFilterRequest().getName()
                ),
                new Pagination(
                       airportSearchRequest.getPagination().getOffset(),
                       airportSearchRequest.getPagination().getLimit()
                )
        );
    }

    public static AirportPaginatedResponse toAirportPaginatedResponse(AirportPaginatedDto airportPaginatedDto) {
        return new AirportPaginatedResponse(
                airportPaginatedDto.getTotal(),
                airportPaginatedDto.getPagination(),
                airportPaginatedDto.getAirports().stream()
                        .map(AirportRequestMapper::toAirportResponse)
                        .collect(Collectors.toList())
        );
    }
}
