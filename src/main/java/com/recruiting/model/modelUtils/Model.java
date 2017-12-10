package com.recruiting.model.modelUtils;

import java.io.Serializable;

/**
 * @author Marta Ginosyan
 */

public abstract class Model<T> implements Serializable {

    private T object;

    public Model() {
    }

    public Model(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
