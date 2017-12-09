package com.recruiting.service.entity;

import com.recruiting.domain.IndividualTimeOff;
import com.recruiting.domain.TimeOffType;
import com.recruiting.repository.IndividualTimeOffRepository;
import com.recruiting.repository.TimeOffTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeOffServiceImpl implements TimeOffService {

    @Autowired
    private TimeOffTypeRepository timeOffTypeRepository;

    @Autowired
    private IndividualTimeOffRepository individualTimeOffRepository;

    @Override
    public List<TimeOffType> getTimeOffTypes() {
        return timeOffTypeRepository.findAll();
    }

    @Override
    public TimeOffType findById(Long id) {
        return timeOffTypeRepository.findOne(id);
    }

    @Override
    public void saveIndividualTimeOff(IndividualTimeOff individualTimeOff) {
        individualTimeOff.setApproved(false);
        individualTimeOff.setDisposed(false);
        individualTimeOffRepository.save(individualTimeOff);
    }
}
