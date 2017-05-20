package com.test.oystercard.bean;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FareCalculatorTest {
    private FareCalculator fareCalculator = new FareCalculator();

    @Test
    public void getZoneWithLeastFare_ShouldReturnMaxValueForBus() {

        double fareForJourney = fareCalculator.getFareForJourney(new Trip(new TripParser("Bus Wimbledon to Hammersmith")));
        assertTrue(fareForJourney == 1.80);
    }

    @Test
    public void getZoneWithLeastFare_ShouldReturnMaxZoneValue() throws Exception {

        ArrayList<Integer> zones = new ArrayList<>();
        zones.add(1);
        zones.add(2);
        int zoneWithLeastFare = fareCalculator.getCloserZone(zones, 3);

        assertEquals(zoneWithLeastFare, 2);
    }

    @Test
    public void getFareForJourney_ShouldReturnValidFareForZone1To2() throws Exception {

        Double fareForJourney = fareCalculator.getFareForJourney(createTrip("Holborn", "Hammersmith"));

        assertTrue(fareForJourney == 3.0);

    }

    @Test
    public void getFareForJourney_ShouldReturnValidFareForZone3To2() throws Exception {

        Double fareForJourney = fareCalculator.getFareForJourney(createTrip("Wimbledon", "Hammersmith"));

        assertTrue(fareForJourney == 2.25);

    }

    @Test
    public void getFareForJourney_ShouldReturnValidFareForZone2To3() throws Exception {

        Double fareForJourney = fareCalculator.getFareForJourney(createTrip("Hammersmith", "Wimbledon"));

        assertTrue(fareForJourney == 2.25);

    }

    @Test
    public void getFareForJourney_ShouldReturnValidFareWithoutToStation() throws Exception {

        Double fareForJourney = fareCalculator.getFareForJourney(createTrip("Holborn", ""));

        assertTrue(fareForJourney == 3.20);

    }

    private Trip createTrip(String from, String to) {
        return new Trip(new TripParser("Tube " + from + " to " + to));
    }

}