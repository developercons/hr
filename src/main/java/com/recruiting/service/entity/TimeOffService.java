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

    TimeOffType findById(String id);


    void saveIndividualTimeOff(IndividualTimeOff individualTimeOff);

    PageWrapper<IndividualTimeOff> getNotApprovedTimeOffRequests(Pageable pageable);

    PageWrapper<IndividualTimeOff> getNotApprovedTimeOffRequests(User user, Pageable pageable);

    void approveTimeOff(String id);

    void disposeTimeOff(String id);

    void deleteTimeOff(String id);
}
