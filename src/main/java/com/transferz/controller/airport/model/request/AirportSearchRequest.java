package com.transferz.controller.airport.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AirportSearchRequest {

    @Valid
    private AirportFilterRequest filterRequest;

    @NotNull
    @Valid
    private PaginationRequest pagination;
}
