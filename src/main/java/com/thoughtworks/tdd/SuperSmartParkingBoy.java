package com.thoughtworks.tdd;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        if(!isCarValid(car)) {
            return null;
        }
        float maxValidPositionRate = 0;
        int maxRateLotIndex = 0;
        for (int i = 0; i < parkingLots.size(); i++) {
            int capacity= parkingLots.get(i).getCapacity();
            float positionNumRate = (float)(capacity - parkingLots.get(i).getCars().size()) / capacity;
            if(positionNumRate > maxValidPositionRate) {
                maxRateLotIndex = i;
                maxValidPositionRate = positionNumRate;
            }
        }
        if(maxValidPositionRate == 0) {
            setErrorMessage("Not enough position.");
            return null;
        }
        return parkingLots.get(maxRateLotIndex).park(car);
    }
}
