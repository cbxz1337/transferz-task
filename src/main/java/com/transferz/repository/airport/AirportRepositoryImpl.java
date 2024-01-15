package com.transferz.repository.airport;

import com.transferz.dao.Airport;
import com.transferz.service.dto.airport.AirportSearchDto;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.transferz.repository.RepositoryUtils.*;

@Repository
@AllArgsConstructor
public class AirportRepositoryImpl implements AirportRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final static String AIRPORT_SEARCH_SQL = """
            select * from airport
            where (:skip_name or "name" like :airport_name)
            and   (:skip_code or code like :airport_code)
            order by name
            limit :limit offset :offset
            """;

    private final static String INSERT_AIRPORT = """
                insert into airport values (
                    :code,
                    :name,
                    :country
                )
            """;

    private final static String GET_AVAILABLE_AIRPORT = """
               SELECT * from airport limit 1;
            """;

    @Override
    public List<Airport> getPaginatedAirportResult(AirportSearchDto searchDto) {
        return jdbcTemplate.query(
                AIRPORT_SEARCH_SQL,
                params(searchDto),
                (rs, r) -> airportFromResultSet(rs)
        );
    }

    @Override
    public Airport getAvailableAirport() {
        return jdbcTemplate.queryForObject(
                GET_AVAILABLE_AIRPORT,
                Map.of(),
                (rs, r) -> airportFromResultSet(rs)
        );
    }

    @Override
    public void createAirport(Airport airport) {
        jdbcTemplate.update(
                INSERT_AIRPORT,
                Map.of(
                        "name", airport.getName(),
                        "code", airport.getCode(),
                        "country", airport.getCountry()
                ));
    }

    private Map<String, Object> params(AirportSearchDto searchDto) {
        return  Map.of(
                "skip_name", searchDto.getFilterRequest().getName() == null,
                "skip_code", searchDto.getFilterRequest().getCode() == null,
                "airport_name", searchDto.getFilterRequest().getName() == null ?
                        EMPTY_STRING : surroundWithWildCards(searchDto.getFilterRequest().getName()),
                "airport_code", searchDto.getFilterRequest().getCode() == null ?
                        EMPTY_STRING : surroundWithWildCards(searchDto.getFilterRequest().getCode()),
                "limit", searchDto.getPagination().getLimit(),
                "offset", searchDto.getPagination().getOffset()
        );
    }
    private Airport airportFromResultSet(ResultSet resultSet) throws SQLException {
        return new Airport(
                resultSet.getString("name"),
                resultSet.getString("code"),
                resultSet.getString("country")
        );
    }
}
