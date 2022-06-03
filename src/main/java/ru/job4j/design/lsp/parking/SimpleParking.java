package ru.job4j.design.lsp.parking;

import java.lang.IllegalArgumentException;

public class SimpleParking implements Parking {

    private Car[][] places = new Car[2][];
    private static final int PASSENGER_INDEX = 0;
    private static final int TRUCK_INDEX = 1;

    /**
     * Переменные searchCacheCar, searchCacheIndexParking, searchCacheIndexPlace представляют собой
     * кеш, который хранит информацию о последнем поиске автомобиля.
     */
    private Car searchCacheCar;
    private int searchCacheIndexParking;
    private int searchCacheIndexPlace;


    public SimpleParking(int passengerPlaces, int truckPlaces) {
        places[PASSENGER_INDEX] = new Car[passengerPlaces];
        places[TRUCK_INDEX] = new Car[truckPlaces];
    }

    /**
     * Метод производит поиск свободного места для автомобиля.
     * @param car Автомобиль, для которого производится поиск свободного места.
     * @return Если есть свободное место возвращается true, в противном случае false.
     */
    public boolean searchSpace(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Illegal argument.");
        }

        boolean rslt;
        if (searchCar(car)) {
            System.out.println("The car is already in the parking lot.");
            rslt = false;
        } else {
            rslt = runSearchSpace(car);
        }
        return rslt;
    }

    private boolean runSearchSpace(Car car) {
        boolean rslt;

        if (car.equals(searchCacheCar)) {
            rslt = true;
        } else if (car.getSize() == PassengerCar.SIZE) {
            rslt = searchSpaceOneCarOneSpace(car, PASSENGER_INDEX);
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
    private boolean searchCar(Car car) {
        boolean rslt = false;
        for (int i = 0; i < places.length; i++) {
            for (int j = 0; j < places[i].length; j++) {
                Car tmp = places[i][j];
                if (tmp != null && tmp.hashCode() == car.hashCode() && tmp.equals(car)) {
                    rslt = true;
                    searchCacheCar = tmp;
                    searchCacheIndexParking = i;
                    searchCacheIndexPlace = j;
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
    private boolean searchSpaceOneCarOneSpace(Car car, int index) {
        boolean rslt = false;
        for (int i = 0; i < places[index].length; i++) {
            if (places[index][i] == null) {
                rslt = true;
                searchCacheCar = car;
                searchCacheIndexParking = index;
                searchCacheIndexPlace = i;
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
    private boolean searchSpaceForTruck(Car car) {
        boolean rslt = searchSpaceOneCarOneSpace(car, TRUCK_INDEX);
        if (!rslt) {
            int count = 0;
            for (int i = 0; i < places[PASSENGER_INDEX].length; i++) {
                if (places[PASSENGER_INDEX][i] == null) {
                    count++;
                    if (count == car.getSize()) {
                        rslt = true;
                        searchCacheCar = car;
                        searchCacheIndexParking = PASSENGER_INDEX;
                        searchCacheIndexPlace = i - car.getSize() + 1;
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
    public boolean parkCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Illegal argument.");
        }
        boolean rslt = false;

        if (searchCar(car)) {
            System.out.println("The car is already in the parking lot.");
        } else if (runSearchSpace(car)) {
            if (searchCacheIndexParking == TRUCK_INDEX) {
                places[searchCacheIndexParking][searchCacheIndexPlace] = car;
            } else {
                for (int i = 0; i < car.getSize(); i++) {
                    places[searchCacheIndexParking][searchCacheIndexPlace + i] = car;
                }
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
    public Car pickUpCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Illegal argument.");
        }

        Car rslt;
        if (searchCar(car)) {
            if (searchCacheIndexParking == TRUCK_INDEX) {
                places[searchCacheIndexParking][searchCacheIndexPlace] = null;
            } else {
                for (int i = 0; i < car.getSize(); i++) {
                    places[searchCacheIndexParking][searchCacheIndexPlace + i] = null;
                }
            }
            rslt = searchCacheCar;
            searchCacheCar = null;
        } else {
            System.out.println("There is no such car in the parking lot.");
            rslt = null;
        }
        return rslt;
    }
}