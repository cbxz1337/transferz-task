package com.transferz.controller.airport;

import com.transferz.controller.airport.model.AirportRequestMapper;
import com.transferz.controller.airport.model.request.AirportSearchRequest;
import com.transferz.controller.airport.model.request.CreateAirportRequest;
import com.transferz.controller.airport.model.response.AirportPaginatedResponse;
import com.transferz.service.airport.AirportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Valid
@AllArgsConstructor
public class AirportControllerImpl implements AirportController {

    private final AirportService airportService;

    @Override
    public AirportPaginatedResponse getAllAirports(
           AirportSearchRequest airportSearchRequest
    ) {
        return AirportRequestMapper.toAirportPaginatedResponse(
                airportService.getPaginatedAirports(AirportRequestMapper.toAirportSearchDto(airportSearchRequest))
        );
    }

    @Override
    public void postAirport(@RequestBody @Valid CreateAirportRequest request) {
        airportService.createAirport(AirportRequestMapper.toAirportDto(request));
    }
}
