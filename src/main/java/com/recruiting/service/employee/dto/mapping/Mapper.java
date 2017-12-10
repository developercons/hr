package com.recruiting.service.employee.dto.mapping;

/**
 * @author Marta Ginosyan
 */

public interface Mapper <M, D>{

    default M mapModel(M model, Class<D> domain){
        return null;
    }

    default D mapDomain(Class<D> domain, M model){
        return null;
    }

}