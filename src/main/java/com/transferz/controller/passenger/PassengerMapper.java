package com.transferz.controller.passenger;

import com.transferz.controller.passenger.model.request.PassengerCreateRequest;
import com.transferz.service.dto.passenger.PassengerCreateDto;

public class PassengerMapper {

    public static PassengerCreateDto toPassengerCreateDto(PassengerCreateRequest request) {
        return new PassengerCreateDto(
                request.getName()
        );
    }
}
