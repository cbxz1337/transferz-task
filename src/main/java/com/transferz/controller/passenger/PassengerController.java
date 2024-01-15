package com.transferz.controller.passenger;

import com.transferz.controller.passenger.model.request.PassengerCreateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("api/v1/passenger")
public interface PassengerController {


    @PostMapping("/")
    void postPassenger(@Valid @RequestBody PassengerCreateRequest request);
}
