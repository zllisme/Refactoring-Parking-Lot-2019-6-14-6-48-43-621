package com.thoughtworks.tdd;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkingCarTest {

    @Test
    public void should_return_car_when_fetch_car_given_ticket_by_parking_the_car() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Ticket ticket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(ticket);
        Assertions.assertSame(car, fetchedCar);
    }

    @Test
    public void should_return_cars_when_fetch_cars_given_tickets_by_parking_the_cars() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Car carTwo = new Car();
        Ticket ticket = parkingBoy.park(car);
        Ticket ticketTwo = parkingBoy.park(carTwo);

        //when
        Car fetchedCar = parkingBoy.fetch(ticket);
        Car fetchedCarTwo = parkingBoy.fetch(ticketTwo);

        //than
        Assertions.assertSame(car, fetchedCar);
        Assertions.assertSame(carTwo, fetchedCarTwo);

    }

    @Test
    public void should_not_return_car_when_fetch_car_given_fake_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        parkingBoy.park(car);

        //when
        Ticket fakeTicket = new Ticket();
        Ticket nullTicket = null;
        Car fetchedCar = parkingBoy.fetch(fakeTicket);
        Car fetchedCarTwo = parkingBoy.fetch(nullTicket);

        //than
        Assertions.assertNull(fetchedCar);
        Assertions.assertNull(fetchedCarTwo);

    }

    @Test
    public void should_not_return_car_when_fatch_car_given_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Ticket usedTicket = parkingBoy.park(car);
        parkingBoy.fetch(usedTicket);

        //when
        Car fetchedCar = parkingBoy.fetch(usedTicket);

        //than
        Assertions.assertNull(fetchedCar);

    }

    @Test
    void should_not_return_ticket_when_park_car_but_parking_lot_has_no_position() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        for (int i = 0; i < 10; i++) {
            Car car = new Car();
            parkingBoy.park(car);
        }

        //when
        Car  extraCar = new Car();
        Ticket ticket = parkingBoy.park(extraCar);

        //than
        Assertions.assertNull(ticket);
    }

    @Test
    public void should_not_return_ticket_when_park_car_given_null_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = null;

        //when
        Ticket ticket = parkingBoy.park(car);

        //than
        Assertions.assertNull(ticket);
    }

    @Test
    public void should_not_return_ticket_when_park_car_given_used_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        parkingBoy.park(car);

        //when
        Ticket ticket = parkingBoy.park(car);

        //than
        Assertions.assertNull(ticket);

    }

    @Test
    public void should_get_error_message_when_fetch_car_given_unrecognized_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Ticket usedTicket = parkingBoy.park(car);
        parkingBoy.fetch(usedTicket);

        //when
        parkingBoy.fetch(usedTicket);
        String message = parkingBoy.queryErrorMessage();

        //than
        MatcherAssert.assertThat(message, CoreMatchers.is("Unrecognized parking ticket."));

    }

    @Test
    public void should_get_error_message_when_fetch_car_given_null_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        parkingBoy.park(car);

        //when
        Ticket nullTicket = null;
        parkingBoy.fetch(nullTicket);
        String errorMessage = parkingBoy.queryErrorMessage();

        //than
        MatcherAssert.assertThat(errorMessage, CoreMatchers.is("Please provide your parking ticket."));

    }

    @Test
    public void should_get_error_message_when_park_car_has_no_position() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        for (int i = 0; i < 10; i++) {
            Car car = new Car();
            parkingBoy.park(car);
        }

        //when
        Car extraCar = new Car();
        parkingBoy.park(extraCar);
        String errorMessage = parkingBoy.queryErrorMessage();

        //than
        MatcherAssert.assertThat(errorMessage, CoreMatchers.is("Not enough position."));

    }

    @Test
    public void should_return_car_when_fetch_car_given_ticket_by_parking_into_second_lot() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingLot parkingLotTwo = new ParkingLot();
        parkingBoy.addParkingLot(parkingLotTwo);
        for (int i = 0; i < 10; i++) {
            parkingBoy.park(new Car());
        }
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);

        //when
        Car fetchedCar = parkingBoy.fetch(ticket);

        //than
        Assertions.assertSame(car, fetchedCar);

    }




}
