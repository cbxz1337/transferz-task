package com.transferz.controller.airport.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateAirportRequest {
    @NotBlank
    @Size(max = 255)
    private String name;
    @NotBlank
    @Size(max = 20)
    private String code;
    @NotBlank
    @Size(max = 60)
    private String country;
}
