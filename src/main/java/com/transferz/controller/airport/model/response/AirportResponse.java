package com.transferz.controller.airport.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AirportResponse {
    private String name;
    private String code;
    private String country;
}
