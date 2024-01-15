package com.transferz.controller.airport;

import com.transferz.controller.airport.model.request.AirportSearchRequest;
import com.transferz.controller.airport.model.request.CreateAirportRequest;
import com.transferz.controller.airport.model.response.AirportPaginatedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Tag(name = "airport service")
@RequestMapping("api/v1/airport")
public interface AirportController {

    @Operation(summary = "search for an airport")
    @PostMapping("/search")
    AirportPaginatedResponse getAllAirports(
            @RequestBody @Valid AirportSearchRequest request
    );

    @Operation( summary = "create an Airport")
    @PostMapping("/")
    void postAirport(CreateAirportRequest request);
}
