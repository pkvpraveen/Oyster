package com.test.oystercard.bean;

import org.junit.Test;

import static org.junit.Assert.*;

public class TripParserTest {

    @Test
    public void getFromStation_ShouldReturnValidFromStationForTube() throws Exception {

        String fromStation = new TripParser("Tube Holborn to Earl's Court").fromStation();

        assertEquals(fromStation, "Holborn");

    }

    @Test
    public void getFromStation_ShouldReturnValidFromStationForBus() throws Exception {

        String fromStation = new TripParser("328 bus from Holborn to Chelsea").fromStation();

        assertEquals(fromStation, "Holborn");

    }

    @Test
    public void getFromStation_ShouldReturnValidToStationForTube() throws Exception {

        String toStation = new TripParser("Tube Holborn to Earl's Court").toStation();

        assertEquals("Earl's Court", toStation);

    }

    @Test
    public void getToStation_ShouldReturnValidToStationForBus() throws Exception {

        String toStation = new TripParser("328 bus from Holborn to Chelsea").toStation();

        assertEquals("Chelsea", toStation);

    }

}