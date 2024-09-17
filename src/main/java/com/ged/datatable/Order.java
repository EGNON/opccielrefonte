package com.ged.datatable;

public class Order {
    Integer column = 0;
    String dir = "";
    String name = "";

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Order{" +
                "column=" + column +
                ", dir='" + dir + '\'' +
                '}';
    }
}
