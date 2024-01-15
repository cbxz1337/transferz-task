package com.transferz.service.dto.flight;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FlightDto {
    private String code;
    private String destinationAirportId;
    private LocalDateTime departureTime;
}
