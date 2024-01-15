package com.transferz.service.dto.airport;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AirportPaginatedDto {
    private int total;
    private Pagination pagination;
    private List<AirportDto> airports;
}
