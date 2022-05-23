package ru.job4j.ocp;

/**
 *	Класс является моделью данных животных. Каждое животное в зависимости от названия
 *	может издавать какой-то звук. Ошибка заключается в том, что приходится каждый раз
 *	менять этот класс при добавлении нового животного. Правильно было бы сделать
 *	родительский абстрактный класс или интерфейс с абстрактным методом sound().
 *	Каждое новое животное должно переопределять метод sound(). Это позволяет расширять
 *	номенклатуру животных и в то же время не менять родительский интерфейс/класс и классы
 *	остальных животных, а вносить изменения только в класс нужного животного.
 */

public class Animal {
    String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void sound(Animal animal) {
        if ("dog".equals(animal.getName())) {
            System.out.println("gav-gav");
        } else if ("cat".equals(animal.getName())) {
            System.out.println("myau");
        } else {
            System.out.println("unknown animal");
        }
    }
}
