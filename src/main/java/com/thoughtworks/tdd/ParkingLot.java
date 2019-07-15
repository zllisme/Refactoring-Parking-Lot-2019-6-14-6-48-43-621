package com.thoughtworks.tdd;


import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity = 10;
    private Map<Ticket, Car> cars = new HashMap<>();

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public Map<Ticket, Car> getCars() {
        return cars;
    }

    public Car getCar(Ticket ticket) {

        return cars.remove(ticket);
    }

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        this.cars.put(ticket, car);
        return ticket;
    }

}
