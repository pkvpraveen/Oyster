package com.test.oystercard.bean;

import com.test.oystercard.constants.StationDetails;

public class TripValidator implements Validator {
    private StationDetails stationDetails = new StationDetails();

    @Override
    public boolean isValid(String tripDescription) {
        boolean isValid = false;
        if (tripDescription.startsWith("Tube") && tripDescription.contains("to")) {
            String fromStation = tripDescription.substring(5, tripDescription.indexOf("to")).trim();
            if (stationDetails.getZonesByStation(fromStation.toLowerCase()) != null) {
                isValid = isValidToStation(tripDescription);
            }
        } else if (tripDescription.contains("bus") && tripDescription.contains("from") && tripDescription.contains("to")) {

            String busNumber = tripDescription.substring(0, tripDescription.indexOf(" "));
            try {
                int busN = Integer.parseInt(busNumber);
                if (busN > 999 && busN < 1) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            String bus = tripDescription.substring(tripDescription.indexOf(" "), tripDescription.indexOf("from")).trim();
            if ("bus".equalsIgnoreCase(bus)) {
                String from = tripDescription.substring(tripDescription.indexOf("bus") + 3, tripDescription.indexOf("from") + 4).trim();
                if ("from".equalsIgnoreCase(from)) {
                    String fromStation = tripDescription.substring(tripDescription.indexOf("from") + 5, tripDescription.indexOf("to")).trim();
                    if (stationDetails.getZonesByStation(fromStation.toLowerCase()) != null) {

                        String to = tripDescription.substring(tripDescription.indexOf(fromStation) + fromStation.length() + 1, tripDescription.indexOf(fromStation) + fromStation.length() + 3);
                        if ("to".equalsIgnoreCase(to)) {

                            isValid = isValidToStation(tripDescription);
                        }
                    }
                }
            }
        }
        return isValid;
    }

    private boolean isValidToStation(String trip) {
        boolean isValid = false;
        if (trip.trim().indexOf("to") + 2 == trip.trim().length()) {
            isValid = true;
        } else {
            String toStation = trip.substring(trip.indexOf("to") + 3, trip.length());
            if (stationDetails.getZonesByStation(toStation.toLowerCase()) != null) {
                isValid = true;
            }
        }
        return isValid;
    }
}
