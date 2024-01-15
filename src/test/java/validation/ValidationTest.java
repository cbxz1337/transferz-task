package validation;

import com.transferz.controller.airport.model.request.CreateAirportRequest;
import com.transferz.controller.passenger.model.request.PassengerCreateRequest;
import lombok.val;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidationTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void PassengerValidationTestNameTooLong() {
        val request = new PassengerCreateRequest();
        request.setName(RandomStringUtils.random(1025));
        val violations = validator.validate(request);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void PassengerValidationTestPositive() {
        val request = new PassengerCreateRequest();
        request.setName(RandomStringUtils.random(100));
        val violations = validator.validate(request);
        Assert.assertTrue(violations.isEmpty());
    }


    @Test
    public void AirportValidationTestAllFieldsEmpty() {
        val request = new CreateAirportRequest();
        request.setCode("");
        request.setCountry("");
        request.setName("");
        val violations = validator.validate(request);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void AirportValidationTestCodeLength() {
        val request = new CreateAirportRequest();
        request.setCode(RandomStringUtils.random(21));
        request.setCountry("Netherlands");
        request.setName("Schiphol");
        val violations = validator.validate(request);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void AirportValidationTestCodePositive() {
        val request = new CreateAirportRequest();
        request.setCode(RandomStringUtils.random(19));
        request.setCountry("Netherlands");
        request.setName("Schiphol");
        val violations = validator.validate(request);
        Assert.assertTrue(violations.isEmpty());
    }
}
