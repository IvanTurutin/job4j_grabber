package ru.job4j.lsp;

/**
 *  Модель данных User имеет инвариант - поле age. Модель данных UnrealUser, которая наследуется от
 *  User не имеет проверки на отрицательный возраст, тем самым нарушает инвариант.
 */

public class User {
    private String name;
    private int age;

    public User() {

    }

    public User(String name, int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст меньше 0!");
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст меньше 0!");
        }
        this.age = age;
    }
}

class UnrealUser extends User {
    private String name;
    private int age;

    public UnrealUser(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
