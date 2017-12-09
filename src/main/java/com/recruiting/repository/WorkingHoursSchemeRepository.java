package com.recruiting.repository;

import com.recruiting.domain.WorkingHoursScheme;

/**
 * @author Marta Ginosyan
 */
public interface WorkingHoursSchemeRepository extends BaseRepository<WorkingHoursScheme> {

    WorkingHoursScheme findByTitle(String title);

    WorkingHoursScheme findFirstByIsValidTrue();
}
