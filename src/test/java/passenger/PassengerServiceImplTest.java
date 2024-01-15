package passenger;

import com.transferz.repository.passenger.PassengerRepository;
import com.transferz.service.dto.flight.FlightDto;
import com.transferz.service.dto.passenger.PassengerCreateDto;
import com.transferz.service.flight.FlightService;
import com.transferz.service.passenger.PassengerService;
import com.transferz.service.passenger.PassengerServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

class PassengerServiceImplTest {


    private final FlightDto testFlightDto1 = new FlightDto("CODE1", "VKO", LocalDateTime.now());

    private final FlightService flightServiceMock = Mockito.mock(FlightService.class);
    private final PassengerRepository passengerRepositoryMock = Mockito.mock(PassengerRepository.class);
    private final PassengerService service = new PassengerServiceImpl(
            flightServiceMock,
            passengerRepositoryMock
    );

    @BeforeEach
    void init() {
        Mockito.reset(
                flightServiceMock,
                passengerRepositoryMock
        );
        ReflectionTestUtils.setField(service, "maxFlightCapacity", 3);
    }

    @Test
    void createPassengerWithAvailableFlight()  {
        Mockito.when(flightServiceMock.chooseFlightForPassenger()).thenReturn(testFlightDto1);
        Mockito.when(passengerRepositoryMock.countPassengersPerFlight(Mockito.anyString())).thenReturn(2);
        service.createPassenger(new PassengerCreateDto("Name 1"));
        Mockito.verify(flightServiceMock, Mockito.never()).createFlight();
        Mockito.verify(flightServiceMock, Mockito.never()).departFlight(Mockito.anyString());

    }

    @Test
    void flightShouldDepart() {
        Mockito.when(flightServiceMock.chooseFlightForPassenger()).thenReturn(testFlightDto1);
        Mockito.when(passengerRepositoryMock.countPassengersPerFlight(Mockito.anyString())).thenReturn(3);
        Mockito.when(flightServiceMock.departFlight(Mockito.anyString())).thenReturn(1);
        service.createPassenger(new PassengerCreateDto("Name 1"));
        Mockito.verify(flightServiceMock, Mockito.never()).createFlight();
        Mockito.verify(flightServiceMock, Mockito.atLeastOnce()).departFlight(Mockito.anyString());
    }

    @Test
    void concurrentModificationTest() {
        Mockito.when(flightServiceMock.chooseFlightForPassenger()).thenReturn(testFlightDto1);
        Mockito.when(passengerRepositoryMock.countPassengersPerFlight(Mockito.anyString())).thenReturn(3);
        Mockito.when(flightServiceMock.departFlight(Mockito.anyString())).thenReturn(0);
        Assert.assertThrows(IllegalArgumentException.class,
                () ->  service.createPassenger(new PassengerCreateDto("Name 1")));
    }
}