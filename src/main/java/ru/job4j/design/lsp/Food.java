package ru.job4j.design.lsp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Food {

    private String name;
    private Calendar expiryDate; /*дата истечения годности*/
    private Calendar createDate; /*дата изготовления*/
    private double price;
    private double discount;

    public Food(String name, Calendar expiryDate, Calendar createDate, double price, double discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public Food() {

    }
    public Food(String name, Calendar expiryDate, Calendar createDate, double price) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Objects.equals(name, food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", expiryDate=" + new SimpleDateFormat("yyyy-MM-dd").format(expiryDate.getTime())
                + ", createDate=" + new SimpleDateFormat("yyyy-MM-dd").format(createDate.getTime())
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
