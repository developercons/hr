package com.recruiting.repository;

import com.recruiting.domain.TimeOffType;

import java.util.List;

/**
 * @author Marta Ginosyan
 */
public interface TimeOffTypeRepository extends BaseRepository<TimeOffType> {

    TimeOffType findByTitle(final String title);

    List<TimeOffType> findAllByIsValidTrue();
}
