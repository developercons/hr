package com.recruiting.model.modelUtils;

import java.io.Serializable;

/**
 * @author Marta Ginosyan
 */

public class StringItemModel extends Model implements Serializable {
    private String type;

    public StringItemModel(String type) {
        this.type = type;
    }

    public StringItemModel() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
