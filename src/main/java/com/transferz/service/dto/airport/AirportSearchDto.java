package com.transferz.service.dto.airport;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AirportSearchDto {

    private AirportFilterDto filterRequest;
    private Pagination pagination;
}
