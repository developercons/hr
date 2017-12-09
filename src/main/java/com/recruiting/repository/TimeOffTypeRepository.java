package com.recruiting.repository;

import com.recruiting.domain.TimeOffType;

/**
 * @author Marta Ginosyan
 */
public interface TimeOffTypeRepository extends BaseRepository<TimeOffType> {

    TimeOffType findByTitle(final String title);

}
