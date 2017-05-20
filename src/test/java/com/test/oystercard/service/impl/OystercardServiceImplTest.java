package com.test.oystercard.service.impl;

import com.test.oystercard.bean.UserDetails;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Ali on 5/14/2017.
 */
public class OystercardServiceImplTest {

    private OystercardServiceImpl oystercardServiceImpl = new OystercardServiceImpl();

    @Test
    public void calcualateBalance_ShouldReturnRemainingBalanceForGivenTripDetailsInput1() throws Exception {

        List<String> listOfTrips = getListOfTrips("Tube Holborn to Earl's Court",
                "328 bus from Earl's Court to Chelsea",
                "Tube Holborn to ",
                "Tube Earl's court to Hammersmith");

        UserDetails userDetails = getUserDetails(10.0, listOfTrips);

        double actualBalance = oystercardServiceImpl.calcualateBalance(userDetails);

        double expectedBalance = 0.5;
        assertTrue(actualBalance == expectedBalance);
    }

    @Test
    public void calcualateBalance_ShouldReturnRemainingBalanceForGivenTripDetailsInput2() throws Exception {

        List<String> listOfTrips = getListOfTrips("Tube Wimbledon to Earl's Court",
                "328 bus from Earl's Court to Chelsea",
                "Tube Holborn to ",
                "Tube Earl's court to Holborn");

        UserDetails userDetails = getUserDetails(10.0, listOfTrips);

        double actualBalance = oystercardServiceImpl.calcualateBalance(userDetails);

        double expectedBalance = 0.25;
        assertTrue(actualBalance == expectedBalance);
    }

    @Test
    public void calcualateBalance_ShouldReturnRemainingBalanceForGivenTripDetailsInput3() throws Exception {

        List<String> listOfTrips = getListOfTrips("Tube Wimbledon to Earl's Court",
                "328 bus from Earl's Court to ",
                "Tube Earl's court to Earl's court",
                "Tube Earl's court to Hammersmith");

        UserDetails userDetails = getUserDetails(10.0, listOfTrips);

        double actualBalance = oystercardServiceImpl.calcualateBalance(userDetails);

        double expectedBalance = 1.95;
        assertTrue(actualBalance == expectedBalance);
    }

    private List<String> getListOfTrips(String... trips) {
        List<String> listOfTrips = new ArrayList<>();
        for (int i = 0; i < trips.length; i++) {
            listOfTrips.add(trips[i]);
        }
        return listOfTrips;
    }

    private UserDetails getUserDetails(double totalBalance,List<String> trips){

        UserDetails userDetails = new UserDetails();
        userDetails.setTrips(trips);
        userDetails.setBalance(totalBalance);
        return userDetails;
    }
}