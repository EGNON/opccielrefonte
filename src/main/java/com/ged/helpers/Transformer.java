package com.ged.helpers;

public class Transformer<T> {
    T entity;

    public Transformer(){}

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
