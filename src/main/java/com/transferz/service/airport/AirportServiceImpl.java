package com.transferz.service.airport;

import com.transferz.dao.Airport;
import com.transferz.repository.airport.AirportRepository;
import com.transferz.service.dto.airport.*;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    @Override
    public void createAirport(AirportDto airportDto) {
        airportRepository.createAirport(new Airport(
                airportDto.getName(),
                airportDto.getCode(),
                airportDto.getCountry()
        ));
    }

    @Override
    public AirportPaginatedDto getPaginatedAirports(AirportSearchDto filterDto) {
        List<Airport> paginatedAirportResult = airportRepository.getPaginatedAirportResult(filterDto);
        return new AirportPaginatedDto(
                paginatedAirportResult.size(),
                filterDto.getPagination(),
                paginatedAirportResult.stream().map(AirportDtoMapper::airportDto).collect(Collectors.toList())
        );
    }

    @Override
    public AirportDto getAvailableAirport() {
        val airport = airportRepository.getAvailableAirport();
        return new AirportDto(
             airport.getName(),
             airport.getCode(),
             airport.getCountry()
        );
    }
}
