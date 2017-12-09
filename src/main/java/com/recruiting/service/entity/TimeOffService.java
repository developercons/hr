package com.recruiting.service.entity;

import com.recruiting.domain.IndividualTimeOff;
import com.recruiting.domain.TimeOffType;

import java.util.List;

public interface TimeOffService {

    List<TimeOffType> getTimeOffTypes();

    TimeOffType findById(Long id);

    void saveIndividualTimeOff(IndividualTimeOff individualTimeOff);
}
