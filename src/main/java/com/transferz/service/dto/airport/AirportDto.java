package com.transferz.service.dto.airport;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AirportDto {
    private String name;
    private String code;
    private String country;
}
