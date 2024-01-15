package com.transferz.controller.passenger;

import com.transferz.controller.passenger.model.request.PassengerCreateRequest;
import com.transferz.service.passenger.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Valid
public class PassengerControllerImpl implements PassengerController {

    private final PassengerService passengerService;

    @Override
    public void postPassenger(@RequestBody @Valid PassengerCreateRequest request) {
        passengerService.createPassenger(PassengerMapper.toPassengerCreateDto(request));
    }
}
