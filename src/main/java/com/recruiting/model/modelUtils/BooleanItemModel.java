package com.recruiting.model.modelUtils;

/**
 * @author Marta Ginosyan
 */

public class BooleanItemModel extends Model {

    private Boolean agreed;

    public BooleanItemModel() {
    }

    public BooleanItemModel(Boolean agreed) {
        this.agreed = agreed;
    }

    public Boolean getAgreed() {
        return agreed;
    }

    public void setAgreed(Boolean agreed) {
        this.agreed = agreed;
    }
}
