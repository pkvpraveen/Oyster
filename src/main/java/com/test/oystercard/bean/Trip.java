package com.test.oystercard.bean;

public class Trip {
    private String tripDescription;
    private String fromStation;
    private String toStation;
    private ModeOfTransport modeOfTransport;
    private boolean isValid;

    public Trip(TripParser parser) {
        this.fromStation = parser.fromStation();
        this.toStation = parser.toStation();
        this.modeOfTransport = parser.modeOfTransport();
        this.isValid = parser.isValid();
        this.tripDescription = parser.getTripDescription();
    }

    public String getFromStation() {
        return fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public ModeOfTransport getModeOfTransport() {
        return modeOfTransport;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getTripDescription() {
        return tripDescription;
    }
}
