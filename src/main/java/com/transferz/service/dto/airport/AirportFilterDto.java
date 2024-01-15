package com.transferz.service.dto.airport;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AirportFilterDto {
    private String code;
    private String name;
}
