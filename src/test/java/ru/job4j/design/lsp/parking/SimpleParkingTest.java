package ru.job4j.design.lsp.parking;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleParkingTest {

    @Ignore
    @Test
    public void whenSearchSpaceThenTrue() {
        Parking parking = new SimpleParking(1, 1);
        Car passCar = new PassengerCar("X123CH78");
        Car truck = new Truck("X987CH78", 2);
        assertTrue(parking.searchSpace(passCar));
        assertTrue(parking.searchSpace(truck));
    }

    @Ignore
    @Test
    public void whenSearchSpaceThenFalse() {
        Parking parking = new SimpleParking(1, 1);
        Car passCar = new PassengerCar("X123CH78");
        Car passCar2 = new PassengerCar("X456CH78");

        Car truck = new Truck("X987CH78", 2);
        Car truck2 = new Truck("X753CH78", 3);

        assertTrue(parking.parkCar(passCar2));
        assertTrue(parking.parkCar(truck2));
        assertFalse(parking.searchSpace(passCar));
        assertFalse(parking.searchSpace(truck));
    }

    @Ignore
    @Test
    public void whenSearchSpaceForTruckThenTrue() {
        Parking parking = new SimpleParking(2, 0);
        Car truck = new Truck("X987CH78", 2);
        assertTrue(parking.searchSpace(truck));
    }

    @Ignore
    @Test
    public void whenParkTwoPassengerCarThenFalse() {
        Parking parking = new SimpleParking(1, 1);
        Car passCar1 = new PassengerCar("X123CH78");
        Car passCar2 = new PassengerCar("X456CH78");
        assertTrue(parking.parkCar(passCar1));
        assertFalse(parking.parkCar(passCar2));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenParkPassengerCarTwice() {
        Parking parking = new SimpleParking(2, 1);
        Car passCar1 = new PassengerCar("X123CH78");
        parking.parkCar(passCar1);
        parking.searchSpace(passCar1);
    }

    @Ignore
    @Test
    public void whenParkTwoTruckThenFalse() {
        Parking parking = new SimpleParking(1, 1);
        Car truck1 = new Truck("X123CH78", 2);
        Car truck2 = new Truck("X456CH78", 3);
        assertTrue(parking.parkCar(truck1));
        assertFalse(parking.parkCar(truck2));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenParkTruckTwice() {
        Parking parking = new SimpleParking(1, 2);
        Car truck1 = new Truck("X123CH78", 2);
        parking.parkCar(truck1);
        parking.searchSpace(truck1);
    }

    @Ignore
    @Test
    public void whenParkTwoTruckThenTrue() {
        Parking parking = new SimpleParking(3, 1);
        Car truck1 = new Truck("X123CH78", 2);
        Car truck2 = new Truck("X456CH78", 3);
        assertTrue(parking.parkCar(truck1));
        assertTrue(parking.parkCar(truck2));
    }

    @Ignore
    @Test
    public void whenParkTruckAndPickUp() {
        Parking parking = new SimpleParking(3, 1);
        Car truck1 = new Truck("X123CH78", 2);
        parking.parkCar(truck1);
        Car result = parking.pickUpCar(truck1);
        assertThat(result, is(truck1));
    }

    @Ignore
    @Test
    public void whenParkPassengerCarAndPickUp() {
        Parking parking = new SimpleParking(3, 1);
        Car passCar1 = new PassengerCar("X123CH78");
        parking.parkCar(passCar1);
        Car result = parking.pickUpCar(passCar1);
        assertThat(result, is(passCar1));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenParkTruckThenPickUpOther() {
        Parking parking = new SimpleParking(3, 1);
        Car truck1 = new Truck("X123CH78", 2);
        parking.parkCar(truck1);
        parking.pickUpCar(new Truck("X456CH78", 3));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenParkPassengerCarThenPickUpOther() {
        Parking parking = new SimpleParking(3, 1);
        Car passCar1 = new PassengerCar("X123CH78");
        parking.parkCar(passCar1);
        parking.pickUpCar(new PassengerCar("X456CH78"));
    }

}