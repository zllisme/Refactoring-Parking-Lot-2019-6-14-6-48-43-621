package com.thoughtworks.tdd;


import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    protected List<ParkingLot> parkingLots= new ArrayList<>();

    protected String errorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public Ticket park(Car car) {
        ParkingLot lastParkingLot = parkingLots.get(parkingLots.size() - 1);
        if(lastParkingLot.getCars().size() == lastParkingLot.getCapacity()) {
            errorMessage = "Not enough position.";
            return null;
        }
        if(!isCarValid(car)){
            return null;
        }
        Ticket ticket = null;
        for(ParkingLot parkingLot : parkingLots) {
            if(parkingLot.getCars().size() < parkingLot.getCapacity()) {
                ticket = parkingLot.park(car);
                break;
            }
        }
        return ticket;
    }

    public boolean isCarValid(Car car) {
        boolean result = true;
        if(car == null){
            result = false;
        }
        for(ParkingLot parkingLot : parkingLots) {
            if(parkingLot.getCars().containsValue(car)) {
                result = false;
            }
        }
        return result;
    }

    public Car fetch(Ticket ticket) {
        if(ticket == null) {
            errorMessage = "Please provide your parking ticket.";
            return null;
        }
        for(ParkingLot parkingLot : parkingLots) {
            if(parkingLot.getCars().containsKey(ticket)) {
                return parkingLot.getCar(ticket);
            }
        }
        errorMessage = "Unrecognized parking ticket.";
        return null;
    }


    public String queryErrorMessage() {
        return this.errorMessage;
    }

}
