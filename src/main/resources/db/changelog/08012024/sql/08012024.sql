CREATE SEQUENCE flight_sequence START 1;

CREATE OR REPLACE FUNCTION generate_flight_code()
    RETURNS VARCHAR AS
$$
DECLARE
    next_value  INTEGER;
    flight_code VARCHAR;
BEGIN
    SELECT nextval('flight_sequence') INTO next_value;
    flight_code := 'RECS' || LPAD(next_value::TEXT, 16, '0');

    RETURN flight_code;
END;
$$
    LANGUAGE plpgsql;

ALTER SEQUENCE flight_sequence RESTART WITH 1;

SELECT generate_flight_code();