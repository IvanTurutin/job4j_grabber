package ru.job4j.gc;

public class User {

    /**
     * Вычисления выполнены для Java HotSpot(TM) 64-Bit Server VM (build 17.0.1+12-LTS-39, mixed mode, sharing)
     * Объект User занимает не менее 96 байт:
     * Заголовок объекта = 16 байт (два машинных слова в 64 разрядной системе)
     * поле типа int = 4 байта
     * ссылочная переменная на объект String = 8 байт
     * поле типа String = минимум 64 байта (пустой объект)
     *              16 байт - заголовок объекта
     *              8 байт - ссылочная переменная на объект byte[] value
     *              не менее 24 байт на объект массива
     *                  16 байт - заголовок объекта
     *                  4 байта - число элементов (int)
     *                  x*2 байта - где x = числу символов в строке
     *                  Размер объекта увеличивается до величины кратной 8 байтам (1 машинное слово 64 бита)
     *              1 байт - byte coder
     *              4 байта - int hash
     *              1 бит - boolean hashIsZero
     *              8 байт - long serialVersionUID
     *              0 байт - static поля (статичная переменная, хранится не в объекте, а в MetaSpace)
     *              Размер объекта увеличивается до величины кратной 8 байтам (1 машинное слово 64 бита)
     * Размер объекта увеличивается до величины кратной 8 байтам (1 машинное слово 64 бита)
     */


    private int id;
    private String name;

    public User(int age, String name) {
        this.id = age;
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d %s%n", id, name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            new User(i, "N" + i);
        }
    }

}
