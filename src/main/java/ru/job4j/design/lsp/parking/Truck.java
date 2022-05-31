package ru.job4j.design.lsp.parking;

import java.util.Objects;

public class Truck implements Car {

    private int size;
    private String number;

    public Truck(String number, int size) {
        this.number = number;
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        return size == truck.size && Objects.equals(number, truck.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, number);
    }

    @Override
    public String toString() {
        return "Truck{"
                + "number='" + number + '\''
                + ", size=" + size
                + '}';
    }
}