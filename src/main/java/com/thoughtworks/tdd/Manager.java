package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;

public class Manager extends ParkingBoy {
    private List<ParkingBoy> parkingBoys;

    public Manager(ParkingLot parkingLot) {
        super(parkingLot);
        this.parkingBoys = new ArrayList<>();
    }

    public Manager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
        super(parkingLots);
        this.parkingBoys = new ArrayList<>();
    }

    public void addParkingBoy(ParkingBoy parkingBoy) {
        parkingBoys.add(parkingBoy);
    }

    public void addParkingBoys(List<ParkingBoy> parkingBoysToADD) {
        parkingBoys.addAll(parkingBoysToADD);
    }

    public List<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }

    public Ticket specifyParkingBoyPark(ParkingBoy parkingBoy, Car car) {
        Ticket ticket = null;
        if(parkingBoys.contains(parkingBoy)) {
            ticket = parkingBoy.park(car);
            if(ticket == null){
                errorMessage = parkingBoy.queryErrorMessage();
            }
        }
        return ticket;
    }

    public Car specifyParkingBoyFetch(ParkingBoy parkingBoy, Ticket ticket) {
        Car car = null;
        if(parkingBoys.contains(parkingBoy)) {
            car = parkingBoy.fetch(ticket);
            if(car == null){
                errorMessage = parkingBoy.queryErrorMessage();
            }
        }
        return car;
    }
}
