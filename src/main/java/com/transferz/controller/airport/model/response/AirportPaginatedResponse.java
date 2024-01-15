package com.transferz.controller.airport.model.response;

import com.transferz.service.dto.airport.Pagination;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class AirportPaginatedResponse {
    private int total;
    private Pagination pagination;
    private List<AirportResponse> airports;
}
