package com.transferz.controller.airport.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
public class PaginationRequest {
    @NotNull
    @PositiveOrZero
    private Integer offset;
    @NotNull
    @Positive
    private Integer limit;
}
