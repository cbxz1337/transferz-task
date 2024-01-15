package com.transferz.controller.passenger.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PassengerCreateRequest {
    @NotBlank
    @Size(max = 1024)
    private String name;
}
