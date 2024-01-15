package com.transferz.repository.passenger;

import com.transferz.dao.Passenger;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@AllArgsConstructor
@Repository
public class PassengerRepositoryImpl implements PassengerRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static String CREATE_PASSENGER_SQL = """
                INSERT INTO PASSENGER
                (name, flight_code)
                values (:name, :flight_code) 
           """;

    private static String COUNT_PASSENGERS_PER_FLIGHT = """
                SELECT count(*) from passenger
                where flight_code = :flight_code
                group by flight_code;
            """;

    @Override
    public void createPassenger(Passenger passenger) {
        jdbcTemplate.update(
                CREATE_PASSENGER_SQL,
                Map.of(
                        "name", passenger.getName(),
                        "flight_code", passenger.getFlightCode()
                        )
                );
    }

    @Override
    @SuppressWarnings("all")
    public int countPassengersPerFlight(String flightCode) {
        return jdbcTemplate.queryForObject(
                COUNT_PASSENGERS_PER_FLIGHT,
                Map.of(
                        "flight_code", flightCode
                ),
                Integer.class
        );
    }
}
