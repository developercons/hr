package com.recruiting.model.modelUtils;

import com.recruiting.domain.AbstractTitledDomain;

import java.io.Serializable;

/**
 * @author Marta Ginosyan
 */

public class CheckboxListModel<T extends AbstractTitledDomain> extends Model implements Serializable {

    private T model;
    private boolean active;

    public CheckboxListModel() {
    }

    public CheckboxListModel(T model, boolean active) {
        this.model = model;
        this.active = active;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
