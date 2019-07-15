package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SuperSmartParkingCarTest {

    @Test
    void should_second_parking_lot_has_two_car_when_park_car_given_larger_available_position_rate() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingLotTwo = new ParkingLot();
        for (int i = 0; i < 5; i++) {
            parkingLot.getCars().put(new Ticket(), new Car());
        }
        parkingLotTwo.setCapacity(5);
        parkingLotTwo.getCars().put(new Ticket(), new Car());
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        superSmartParkingBoy.addParkingLot(parkingLotTwo);

        //when
        superSmartParkingBoy.park(new Car());

        //than
        Assertions.assertEquals(2, parkingLotTwo.getCars().size());

    }
}
