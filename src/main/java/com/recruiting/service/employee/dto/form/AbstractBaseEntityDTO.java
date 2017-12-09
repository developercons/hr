package com.recruiting.service.employee.dto.form;

import com.recruiting.domain.AbstractDomain;

/**
 * @author sergey, created on 11/17/17.
 */
public abstract class AbstractBaseEntityDTO<T extends AbstractDomain> {

    public AbstractBaseEntityDTO() {
    }

    /* Abstract methods */
    public abstract T updateDomainEntityPlainProperties(final T domainEntity);

    /* Equals, HashCode and ToString */

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
