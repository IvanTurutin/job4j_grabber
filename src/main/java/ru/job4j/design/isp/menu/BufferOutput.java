package ru.job4j.design.isp.menu;

public class BufferOutput implements Output {
    private final StringBuilder buffer = new StringBuilder();

    @Override
    public void println(Object obj) {
        if (obj != null) {
            buffer.append(obj.toString());
        } else {
            buffer.append("null");
        }
        buffer.append(System.lineSeparator());
    }

    @Override
    public void print(Object obj) {
        if (obj != null) {
            buffer.append(obj.toString());
        } else {
            buffer.append("null");
        }
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

}
