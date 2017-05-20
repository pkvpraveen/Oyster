package com.test.oystercard.service.impl;

import com.test.oystercard.bean.FareCalculator;
import com.test.oystercard.bean.Trip;
import com.test.oystercard.bean.UserDetails;
import com.test.oystercard.constants.StationDetails;
import com.test.oystercard.service.OystercardService;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import static java.lang.Double.parseDouble;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * Created by Ali on 5/14/2017.
 */
public class OystercardServiceImpl implements OystercardService {

    private final FareCalculator fareCalculator = new FareCalculator();
    private StationDetails stationDetails = new StationDetails();
    private static DecimalFormat df2 = new DecimalFormat(".##");


    /**
     * This method takes userDetails as input, validate users journey, calculate users remaining balance based on
     * user's journey in different zones.
     *
     * @param userDetails
     * @return remaining balance
     */
    @Override
    public double calcualateBalance(UserDetails userDetails) {

        List<Trip> tripList = userDetails.getTrips();
        List<Trip> validTrips = filterInvalidTrips(tripList);
        Map<String, Double> fareDetails = getFareDetailsByTrip(validTrips);
        displayfares(tripList, fareDetails);
        Double balance = getRemainingBalance(userDetails.getBalance(), validTrips, fareDetails);
        return parseDouble(df2.format(balance));
    }

    private Map<String, Double> getFareDetailsByTrip(List<Trip> validTrips) {
        return validTrips.stream()
                    .collect(toMap(Trip::getTripDescription, fareCalculator::getFareForJourney));
    }

    private List<Trip> filterInvalidTrips(List<Trip> tripList) {
        return tripList.stream()
                    .filter(Trip::isValid).collect(toList());
    }

    /**
     * This method takes user's total balance and calculate remaining balance based on user's valid journey
     *
     * @param previousBalance
     * @param trips
     * @param fareDetails
     * @return
     */
    private double getRemainingBalance(double previousBalance, List<Trip> trips, Map<String, Double> fareDetails) {
        Double totalFare = trips.stream()
                .map(trip -> fareDetails.get(trip.getTripDescription()))
                .reduce(Double::sum)
                .orElse(0.0);
        return previousBalance - totalFare;
    }

    private void displayfares(List<Trip> trips, Map<String, Double> fareDetails) {
        trips.forEach(trip -> {
            if (trip.isValid()) {
                System.out.println("Fare for trip -> " + trip.getTripDescription() + " = " + fareDetails.get(trip.getTripDescription()));
            } else {
                System.out.println("Invalid trip -> " + trip.getTripDescription());
            }
        });
    }


}
