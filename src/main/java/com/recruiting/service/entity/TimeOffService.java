package com.recruiting.service.entity;

import com.recruiting.domain.IndividualTimeOff;
import com.recruiting.domain.TimeOffType;
import com.recruiting.domain.User;
import com.recruiting.model.modelUtils.PageWrapper;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Marta Ginosyan
 */

public interface TimeOffService {

    List<TimeOffType> getTimeOffTypes();

    List<TimeOffType> getValidTimeOffTypes();

    TimeOffType findById(Long id);


    void saveIndividualTimeOff(IndividualTimeOff individualTimeOff);

    PageWrapper<IndividualTimeOff> getNotApprovedTimeOffRequests(Pageable pageable);

    PageWrapper<IndividualTimeOff> getNotApprovedTimeOffRequests(User user, Pageable pageable);

    void approveTimeOff(Long id);

    void disposeTimeOff(Long id);

    void deleteTimeOff(Long id);
}
