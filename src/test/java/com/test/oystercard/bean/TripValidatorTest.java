package com.test.oystercard.bean;

import org.junit.Test;

import static org.junit.Assert.*;

public class TripValidatorTest {
    private Validator validator = new TripValidator();


    @Test
    public void isValidateTrip_ShouldReturnFalseForInValidStringTokenFrom() throws Exception {

        boolean validTrip = validator.isValid("Tube Holborn from Earl's court");

        assertFalse(validTrip);
    }

    @Test
    public void isValidateTrip_ShouldReturnFalseForInValidFromStation() throws Exception {

        boolean validTrip = validator.isValid("Tube stratford to Earl's court");

        assertFalse(validTrip);
    }

    @Test
    public void isValidateTrip_ShouldReturnFalseForInValidToStation() throws Exception {

        boolean validTrip = validator.isValid("Tube holborn to oxford");

        assertFalse(validTrip);
    }

    @Test
    public void isValidateTrip_ShouldReturnTrueForValidTrip() throws Exception {

        boolean validateTrip = validator.isValid("Tube Holborn to Earl's Court");

        assertTrue(validateTrip);

    }

    @Test
    public void isValidateTrip_ShouldReturnTrueWithoutToStation() throws Exception {

        boolean validateTrip = validator.isValid("Tube Holborn to ");

        assertTrue(validateTrip);

    }

    @Test
    public void isValidateTrip_ShouldReturnTrueForValidBusTrip() throws Exception {

        boolean isValid = validator.isValid("328 bus from Holborn to Chelsea");

        assertTrue(isValid);

    }

    @Test
    public void isValidateTrip_ShouldReturnFalseForInValidBusNumber() throws Exception {

        boolean isValid = validator.isValid("bus bus from Holborn to Chelsea");

        assertFalse(isValid);

    }

    @Test
    public void isValidateTrip_ShouldReturnFalseForMissingBusToken() throws Exception {

        boolean isValid = validator.isValid("328 from Holborn to Chelsea");

        assertFalse(isValid);

    }

    @Test
    public void isValidateTrip_ShouldReturnFalseForMissingFromToken() throws Exception {

        boolean isValid = validator.isValid("328 bus Holborn to Chelsea");

        assertFalse(isValid);

    }

    @Test
    public void isValidateTrip_ShouldReturnFalseForMissingToToken() throws Exception {

        boolean isValid = validator.isValid("328 bus from Holborn Chelsea");

        assertFalse(isValid);

    }

    @Test
    public void isValidateTrip_ShouldReturnFalseForInvalidFromStation() throws Exception {

        boolean isValid = validator.isValid("328 bus from starford to Chelsea");

        assertFalse(isValid);

    }

    @Test
    public void isValidateTrip_ShouldReturnFalseForInvalidtoStation() throws Exception {

        boolean isValid = validator.isValid("328 bus from holborn to stratford");

        assertFalse(isValid);

    }

}