package com.test.oystercard.bean;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Ali on 5/14/2017.
 */
public class UserDetails {

    private String userName;
    private Double balance;
    private List<String> trips;


    public void setTrips(List<String> trips) {
        this.trips = trips;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Trip> getTrips() {
        return trips.stream()
                .map(tripDescription -> new Trip(new TripParser(tripDescription)))
                .collect(toList());
    }
}
