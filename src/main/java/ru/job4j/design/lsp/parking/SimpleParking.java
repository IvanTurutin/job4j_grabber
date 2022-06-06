package ru.job4j.design.lsp.parking;

import java.lang.IllegalArgumentException;
import java.util.Arrays;

public class SimpleParking implements Parking {

    private Car[][] places = new Car[2][];
    private static final int PASSENGER_PARKING = 0;
    private static final int TRUCK_PARKING = 1;
    private static final int PARKING_INDEX = 0;
    private static final int PLACE_INDEX = 1;

    public SimpleParking(int passengerPlaces, int truckPlaces) {
        places[PASSENGER_PARKING] = new Car[passengerPlaces];
        places[TRUCK_PARKING] = new Car[truckPlaces];
    }

    /**
     * Метод производит поиск свободного места для автомобиля.
     * @param car Автомобиль, для которого производится поиск свободного места.
     * @return Если есть свободное место возвращается true, в противном случае false.
     */
    @Override
    public boolean searchSpace(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Illegal argument.");
        }

        boolean rslt = false;
        if (searchCar(car) != null) {
            System.out.println("The car is already in the parking lot.");
        } else if (runSearchSpace(car) != null) {
            rslt = true;
        }
        return rslt;
    }

    private int[] runSearchSpace(Car car) {
        int[] rslt;
        if (car.getSize() == PassengerCar.SIZE) {
            rslt = searchSpaceOneCarOneSpace(car, PASSENGER_PARKING);
        } else {
            rslt = searchSpaceForTruck(car);
        }
        return rslt;
    }

    /**
     * Метод производит поиск автомобиля на парковке.
     * @param car автомобиль, который необходимо найти
     * @return true если автомобиль найден, false если не найден.
     */
    private int[] searchCar(Car car) {
        int[] rslt = null;
        for (int i = 0; i < places.length; i++) {
            for (int j = 0; j < places[i].length; j++) {
                Car tmp = places[i][j];
                if (tmp != null && tmp.hashCode() == car.hashCode() && tmp.equals(car)) {
                    rslt = new int[]{i, j};
                    break;
                }
            }
        }
        return rslt;
    }

    /**
     * Метод осуществляет поиск места для случая 1 автомобиль на 1 место.
     * @param car автомобиль который требуется запарковать.
     * @param index индекс парковки: 0 - парковка пассажирских автомобилей, 1 - парковка грузовых автомобилей.
     * @return true если место есть, false если места нет.
     */
    private int[] searchSpaceOneCarOneSpace(Car car, int index) {
        int[] rslt = null;
        for (int i = 0; i < places[index].length; i++) {
            if (places[index][i] == null) {
                rslt = new int[]{index, i};
                break;
            }
        }
        return rslt;
    }

    /**
     * Метод осуществляет поиск парковочного места для грузовика сначала в массиве для грузовых
     * автомобилей и если место не найдено, то осуществляется поиск по массиву для пассажирских автомобилей.
     * @param car автомобиль, для которого требуется найти место
     * @return true если место найдено, false если место не найдено.
     */
    private int[] searchSpaceForTruck(Car car) {
        int[] rslt = searchSpaceOneCarOneSpace(car, TRUCK_PARKING);
        if (rslt == null) {
            int count = 0;
            for (int i = 0; i < places[PASSENGER_PARKING].length; i++) {
                if (places[PASSENGER_PARKING][i] == null) {
                    count++;
                    if (count == car.getSize()) {
                        rslt = new int[]{PASSENGER_PARKING, i - (car.getSize() - 1)};
                        break;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return rslt;
    }

    /**
     * Метод производит парковку автомобиля при наличии места.
     * @param car Автомобиль, который нужно запарковать.
     * @return Если есть свободное место и автомобиль запаркован возвращается true, в противном случае false.
     * @exception IllegalArgumentException В случае если автомобиля на парковке уже есть выбрасывается
     * исключение IllegalArgumentException.
     */
    @Override
    public boolean parkCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Illegal argument.");
        }
        if (searchCar(car) != null) {
            System.out.println("The car is already in the parking lot.");
            return false;
        }

        boolean rslt = false;

        int[] searchRslt = runSearchSpace(car);
        if (searchRslt != null) {
            if (searchRslt[PARKING_INDEX] == TRUCK_PARKING) {
                places[searchRslt[PARKING_INDEX]][searchRslt[PLACE_INDEX]] = car;
            } else {
                Arrays.fill(
                        places[searchRslt[PARKING_INDEX]],
                        searchRslt[PLACE_INDEX],
                        searchRslt[PLACE_INDEX] + car.getSize(),
                        car
                );
            }
            rslt = true;
        }
        return rslt;
    }

    /**
     * Метод производит исключение автомобиля с парковки.
     * @param car Автомобиль, который необходимо исключить с парковки.
     * @return В случае, если автомобиль запаркован на этой парковке - возвращается его объект.
     * @exception IllegalArgumentException В случае если автомобиля на парковке нет выбрасывается
     * исключение IllegalArgumentException.
     */
    @Override
    public Car pickUpCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Illegal argument.");
        }

        Car rslt;
        int[] searchRslt = searchCar(car);
        if (searchRslt != null) {
            rslt = places[searchRslt[PARKING_INDEX]][searchRslt[PLACE_INDEX]];
            if (searchRslt[PARKING_INDEX] == TRUCK_PARKING) {
                places[searchRslt[PARKING_INDEX]][searchRslt[PLACE_INDEX]] = null;
            } else {
                Arrays.fill(
                        places[searchRslt[PARKING_INDEX]],
                        searchRslt[PLACE_INDEX],
                        searchRslt[PLACE_INDEX] + car.getSize(),
                        null
                );
            }
        } else {
            System.out.println("There is no such car in the parking lot.");
            rslt = null;
        }
        return rslt;
    }
}