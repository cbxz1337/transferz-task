package com.transferz.repository.flight;

import com.transferz.dao.Flight;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class FlightRepositoryImpl implements FlightRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private static String NEXT_FLIGHT = """
            select * from flight 
            where departure_date_time is null 
            order by code limit 1; 
            """;

    private static final String UPDATE_FLIGHT = """
            update flight 
            set departure_date_time = :departure_date
            where code = :flight_id and departure_date_time is null;
            """;

    private static final String CREATE_FLIGHT = """
            insert into flight 
            (code, destination_airport_code)
            values (generate_flight_code(), :airport_code)
            returning code;
            """;


    @Override
    public Flight nextFlight() {
        return namedParameterJdbcTemplate.queryForObject(
                NEXT_FLIGHT,
                Map.of(),
                (rs, r) -> fromResultSet(rs)
        );
    }

    @Override
    public String createFlight(String destAirport) {
        return namedParameterJdbcTemplate.queryForObject(
                CREATE_FLIGHT,
                Map.of("airport_code", destAirport),
                (rs, r) -> rs.getString("code")
        );
    }

    @Override
    public int updateFlightDepartureInfo(Flight flight) {
        return namedParameterJdbcTemplate.update(
                UPDATE_FLIGHT,
                Map.of(
                        "flight_id", flight.getCode(),
                        "departure_date", flight.getDepartureTime()
                )
        );
    }

    private Flight fromResultSet(ResultSet resultSet) throws SQLException  {
        return new Flight(
               resultSet.getString("code"),
               resultSet.getString("destination_airport_code"),
                Optional.ofNullable(resultSet.getTimestamp("departure_date_time"))
                        .map(Timestamp::toLocalDateTime).orElse(null)
        );
    }
}
