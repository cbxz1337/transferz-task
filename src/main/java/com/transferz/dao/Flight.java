package com.transferz.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Flight
{
	private String code;

	private String destinationAirportId;

	private LocalDateTime departureTime;


}
