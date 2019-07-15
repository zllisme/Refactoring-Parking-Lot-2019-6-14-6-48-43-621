package com.thoughtworks.tdd;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SmartParkingCarTest {

    @Test
    void should_second_parking_lot_has_two_car_when_park_car_given_less_car_into_second_lot() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingLotTwo = new ParkingLot();
        parkingLot.getCars().put(new Ticket(), new Car());
        parkingLot.getCars().put(new Ticket(), new Car());
        parkingLotTwo.getCars().put(new Ticket(), new Car());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        smartParkingBoy.addParkingLot(parkingLotTwo);
        //when
        smartParkingBoy.park(new Car());

        //than
        Assertions.assertEquals(2, parkingLotTwo.getCars().size());

    }

    @Test
    void should_not_return_ticket_when_park_car_but_parking_lot_has_no_position() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        for (int i = 0; i < 10; i++) {
            Car car = new Car();
            smartParkingBoy.park(car);
        }

        //when
        Car  extraCar = new Car();
        Ticket ticket = smartParkingBoy.park(extraCar);

        //than
        Assertions.assertNull(ticket);
    }

    @Test
    public void should_not_return_ticket_when_park_car_given_null_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        Car car = null;

        //when
        Ticket ticket = smartParkingBoy.park(car);

        //than
        Assertions.assertNull(ticket);
    }

    @Test
    public void should_not_return_ticket_when_park_car_given_used_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        Car car = new Car();
        smartParkingBoy.park(car);

        //when
        Ticket ticket = smartParkingBoy.park(car);

        //than
        Assertions.assertNull(ticket);

    }

    @Test
    public void should_get_error_message_when_park_car_has_no_position() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        for (int i = 0; i < 10; i++) {
            Car car = new Car();
            smartParkingBoy.park(car);
        }

        //when
        Car extraCar = new Car();
        smartParkingBoy.park(extraCar);
        String errorMessage = smartParkingBoy.queryErrorMessage();

        //than
        MatcherAssert.assertThat(errorMessage, CoreMatchers.is("Not enough position."));

    }

    @Test
    public void should_get_error_message_when_fetch_car_given_unrecognized_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        Car car = new Car();
        Ticket usedTicket = smartParkingBoy.park(car);
        smartParkingBoy.fetch(usedTicket);

        //when
        smartParkingBoy.fetch(usedTicket);
        String message = smartParkingBoy.queryErrorMessage();

        //than
        MatcherAssert.assertThat(message, CoreMatchers.is("Unrecognized parking ticket."));

    }

    @Test
    public void should_return_car_when_fetch_car_given_ticket_by_parking_the_car() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        Ticket ticket = smartParkingBoy.park(car);
        Car fetchedCar = smartParkingBoy.fetch(ticket);
        Assertions.assertSame(car, fetchedCar);
    }

    @Test
    public void should_return_cars_when_fetch_cars_given_tickets_by_parking_the_cars() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        Car car = new Car();
        Car carTwo = new Car();
        Ticket ticket = smartParkingBoy.park(car);
        Ticket ticketTwo = smartParkingBoy.park(carTwo);

        //when
        Car fetchedCar = smartParkingBoy.fetch(ticket);
        Car fetchedCarTwo = smartParkingBoy.fetch(ticketTwo);

        //than
        Assertions.assertSame(car, fetchedCar);
        Assertions.assertSame(carTwo, fetchedCarTwo);

    }

    @Test
    public void should_not_return_car_when_fetch_car_given_fake_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        Car car = new Car();
        smartParkingBoy.park(car);

        //when
        Ticket fakeTicket = new Ticket();
        Ticket nullTicket = null;
        Car fetchedCar = smartParkingBoy.fetch(fakeTicket);
        Car fetchedCarTwo = smartParkingBoy.fetch(nullTicket);

        //than
        Assertions.assertNull(fetchedCar);
        Assertions.assertNull(fetchedCarTwo);

    }

    @Test
    public void should_not_return_car_when_fatch_car_given_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        Car car = new Car();
        Ticket usedTicket = smartParkingBoy.park(car);
        smartParkingBoy.fetch(usedTicket);

        //when
        Car fetchedCar = smartParkingBoy.fetch(usedTicket);

        //than
        Assertions.assertNull(fetchedCar);

    }

}
