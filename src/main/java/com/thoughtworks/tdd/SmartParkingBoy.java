package com.thoughtworks.tdd;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy{
    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
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
        int maxPositionNum = 0;
        int maxPositionLotIndex = 0;
        for (int i = 0; i < parkingLots.size(); i++) {
            int positionNum = parkingLots.get(i).getCapacity() - parkingLots.get(i).getCars().size();
            if(positionNum > maxPositionNum) {
                maxPositionLotIndex = i;
                maxPositionNum = positionNum;
            }
        }
        if(maxPositionNum == 0) {
            setErrorMessage("Not enough position.");
            return null;
        }
        return parkingLots.get(maxPositionLotIndex).park(car);
    }


}
