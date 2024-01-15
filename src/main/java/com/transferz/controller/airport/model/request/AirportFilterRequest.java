package com.transferz.controller.airport.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class AirportFilterRequest {
    @Size(min = 1)
    private String name;
    @Size(min = 1)
    private String code;
}
