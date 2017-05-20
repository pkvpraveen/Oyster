package com.test.oystercard.bean;

public class TripParser {
    private String tripDescription;
    private Validator validator = new TripValidator();

    public TripParser(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    public String fromStation() {
        String fromStation = null;
        if (tripDescription.startsWith("Tube")) {
            fromStation = tripDescription.substring(5, tripDescription.indexOf("to")).trim();

        } else if (tripDescription.contains("bus")) {
            fromStation = tripDescription.substring(tripDescription.indexOf("from") + 5, tripDescription.indexOf("to")).trim();
        }
        return fromStation;
    }

    public String toStation() {
        String toStation = null;

        if (tripDescription.trim().indexOf("to") + 2 == tripDescription.trim().length()) {
            return "";
        }
        if (tripDescription.startsWith("Tube")) {
            toStation = tripDescription.substring(tripDescription.indexOf("to") + 3, tripDescription.length());

        } else if (tripDescription.contains("bus")) {
            toStation = tripDescription.substring(tripDescription.indexOf("to") + 3, tripDescription.length());
        }
        return toStation;
    }

    public ModeOfTransport modeOfTransport() {
        return tripDescription.startsWith("Tube") ?
                ModeOfTransport.TUBE : ModeOfTransport.BUS;
    }

    public boolean isValid() {
        return validator.isValid(tripDescription);
    }

    public String getTripDescription() {
        return tripDescription;
    }
}
