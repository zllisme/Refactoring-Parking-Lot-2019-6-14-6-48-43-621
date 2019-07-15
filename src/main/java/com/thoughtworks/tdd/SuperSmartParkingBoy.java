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
        if(car == null){
            return null;
        }
        for(ParkingLot parkingLot : parkingLots) {
            if(parkingLot.getCars().containsValue(car)) {
                return null;
            }
        }
        float maxPositionNumRate = 0;
        int maxPositionRateLotIndex = 0;
        //int totalCapacity = parkingLots.stream().map(obj -> obj.getCapacity()).reduce(0, (pre, curr) -> pre + curr);
        for (int i = 0; i < parkingLots.size(); i++) {
            float capacity= parkingLots.get(i).getCapacity();
            float positionNumRate = (capacity - parkingLots.get(i).getCars().size()) / capacity;
            if(positionNumRate > maxPositionNumRate) {
                maxPositionRateLotIndex = i;
                maxPositionNumRate = positionNumRate;
            }
        }
        if(maxPositionNumRate == 0) {
            setErrorMessage("Not enough position.");
            return null;
        }
        return parkingLots.get(maxPositionRateLotIndex).park(car);
    }
}
