package com.thoughtworks.tdd;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManagerTest {

    @Test
    public void should_parking_boy_list_has_one_when_add_parking_boy_given_a_parking_boy() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingLot parkingLotTwo = new ParkingLot();
        Manager manager = new Manager(parkingLotTwo);
        manager.addParkingBoy(parkingBoy);

        //when
        int actualSize = manager.getParkingBoys().size();

        //than
        Assertions.assertEquals(1, actualSize);

    }

    @Test
    void should_return_error_message_when_specify_park_car_but_parking_lot_has_no_position() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        for (int i = 0; i < 10; i++) {
            Car car = new Car();
            parkingBoy.park(car);
        }
        ParkingLot parkingLotTwo = new ParkingLot();
        Manager manager = new Manager(parkingLotTwo);
        manager.addParkingBoy(parkingBoy);

        //when
        Car extraCar = new Car();
        manager.specifyParkingBoyPark(parkingBoy, extraCar);
        String errorMessage = manager.queryErrorMessage();

        //than
        MatcherAssert.assertThat(errorMessage, CoreMatchers.is("Not enough position."));
    }

    @Test
    void should_return_error_message_when_specify_fetch_car_given_null_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingLot parkingLotTwo = new ParkingLot();
        Manager manager = new Manager(parkingLotTwo);
        manager.addParkingBoy(parkingBoy);
        Car car = new Car();
        parkingBoy.park(car);


        //when
        Ticket nullTicket = null;
        manager.specifyParkingBoyFetch(parkingBoy, null);
        String errorMessage = manager.queryErrorMessage();

        //than
        MatcherAssert.assertThat(errorMessage, CoreMatchers.is("Please provide your parking ticket."));
    }
}
