package ru.job4j.design.lsp.parking;

public class SimpleParking implements Parking {

    private Car[][] places = new Car[2][];

    public SimpleParking(int passengerPlaces, int truckPlaces) {
        places[0] = new Car[passengerPlaces];
        places[1] = new Car[truckPlaces];
    }

    /**
     * Метод searchSpace() производит кеширование позиции, в которой есть свободное место для последнего поиска.
     * В этом кеше должен храниться объект, для которого производился поиск и позиция в массиве,
     * куда его стоит поместить.
     * Это можно реализовать внутренним классом.
     */

    /**
     * Метод производит поиск свободного места для автомобиля.
     * @param car Автомобиль, для которого производится поиск свободного места.
     * @return Если есть свободное место возвращается true, в противном случае false.
     * @exception IllegalArgumentException В случае если автомобиль уже запаркован на этой парковке.
     */
    public boolean searchSpace(Car car) {
        return false;
    }

    /**
     * Метод производит парковку автомобиля при наличии места.
     * @param car Автомобиль, который нужно запарковать.
     * @return Если есть свободное место и автомобиль запаркован возвращается true, в противном случае false.
     */
    public boolean parkCar(Car car) {
        return false;
    }

    /**
     * Метод производит исключение автомобиля с парковки.
     * @param car Автомобиль, который необходимо исключить с парковки.
     * @return В случае, если автомобиль запаркован на этой парковке - возвращается его объект.
     * @exception IllegalArgumentException В случае если автомобиля нет выбрасывается исключение IllegalArgumentException.
     */
    public Car pickUpCar(Car car) {
        return null;
    }
}
