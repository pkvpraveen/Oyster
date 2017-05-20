package com.test.oystercard.bean;

import com.test.oystercard.constants.StationDetails;

import java.util.List;

import static com.test.oystercard.bean.ModeOfTransport.BUS;

public class FareCalculator {
    private StationDetails stationDetails = new StationDetails();

    public double getFareForJourney(Trip trip) {
        if (trip.getModeOfTransport() == BUS) {
            return stationDetails.getZoneFareByZone("bus");
        }
        String toStation = trip.getToStation() != null ? trip.getToStation() : "";
        String fromStation = trip.getFromStation() != null ? trip.getFromStation() : "";

        double maxFare = 3.2;
        if (toStation.length() == 0) {
            return maxFare;
        }
        double fare = 0;

        List<Integer> fromStationZones = stationDetails.getZonesByStation(fromStation.toLowerCase());
        List<Integer> toStationZones = stationDetails.getZonesByStation(toStation.toLowerCase());
        int fromZone = 0;
        int toZone = 0;

        if (fromStationZones.size() > 1 && toStationZones.size() > 1) {

            if (fromStationZones.get(1) == toStationZones.get(1)) {
                fromZone = fromStationZones.get(1);
                toZone = toStationZones.get(1);

            } else if (fromStationZones.get(1) == toStationZones.get(0)) {
                fromZone = fromStationZones.get(1);
                toZone = toStationZones.get(0);

            } else if (fromStationZones.get(0) == toStationZones.get(1)) {
                fromZone = fromStationZones.get(0);
                toZone = toStationZones.get(1);
            }

        } else if (fromStationZones.size() > 1 && toStationZones.size() == 1) {

            toZone = toStationZones.get(0);
            fromZone = getCloserZone(fromStationZones, toZone);

        } else if (fromStationZones.size() == 1 && toStationZones.size() > 1) {

            fromZone = fromStationZones.get(0);
            toZone = getCloserZone(toStationZones, fromZone);
        } else {

            fromZone = fromStationZones.get(0);
            toZone = toStationZones.get(0);
        }

        fare = stationDetails.getZoneFareByZone(fromZone + "," + toZone);

        return fare;
    }

    /**
     * This method takes list of zones for a given station and find closer zone to reduce customer's fare
     *
     * @param zonesByStation
     * @param zone
     * @return closest zone to reduce fare
     */
    public int getCloserZone(List<Integer> zonesByStation, Integer zone) {

        int diff = Integer.MAX_VALUE;
        int closeZone = zonesByStation.get(0);
        for (Integer z : zonesByStation) {
            if (Math.abs(zone - z) < diff) {
                diff = Math.abs(zone - z);
                closeZone = z;
            }
        }
        return closeZone;
    }

}
