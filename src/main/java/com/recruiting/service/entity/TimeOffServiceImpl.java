package com.recruiting.service.entity;

import com.recruiting.domain.IndividualTimeOff;
import com.recruiting.domain.TimeOffType;
import com.recruiting.domain.User;
import com.recruiting.model.modelUtils.PageWrapper;
import com.recruiting.repository.IndividualTimeOffRepository;
import com.recruiting.repository.TimeOffTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Marta Ginosyan
 */

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
    public List<TimeOffType> getValidTimeOffTypes() {
        return timeOffTypeRepository.findAllByIsValidTrue();
    }

    @Override
    public TimeOffType findById(Long id) {
        return timeOffTypeRepository.findOne(id);
    }

    @Override
    public void saveIndividualTimeOff(IndividualTimeOff individualTimeOff) {
        individualTimeOff.setApproved(false);
        individualTimeOff.setDisposed(false);
        individualTimeOff.setDisposed_hr(false);
        individualTimeOffRepository.save(individualTimeOff);
    }

    @Override
    public PageWrapper<IndividualTimeOff> getNotApprovedTimeOffRequests(Pageable pageable) {
        Page<IndividualTimeOff> timeOffs = individualTimeOffRepository.findAllByApprovedFalse(pageable);
        if (!timeOffs.hasContent()) {
            return new PageWrapper<>();
        }
        PageWrapper<IndividualTimeOff> pageWrapper = new PageWrapper<>(timeOffs, "");
        return pageWrapper;
    }

    @Override
    public PageWrapper<IndividualTimeOff> getNotApprovedTimeOffRequests(User user, Pageable pageable) {
        Page<IndividualTimeOff> timeOffs = individualTimeOffRepository.findAllByApprovedFalseAndUser(user, pageable);
        if (!timeOffs.hasContent()) {
            return new PageWrapper<>();
        }
        PageWrapper<IndividualTimeOff> pageWrapper = new PageWrapper<>(timeOffs, "");
        return pageWrapper;
    }

    @Override
    public void approveTimeOff(Long id) {
        IndividualTimeOff individualTimeOff = individualTimeOffRepository.findOne(id);
        individualTimeOff.setApproved(true);
        individualTimeOffRepository.save(individualTimeOff);

    }

    @Override
    public void disposeTimeOff(Long id) {
        IndividualTimeOff individualTimeOff = individualTimeOffRepository.findOne(id);
        if (!individualTimeOff.getReason()
                .getDisposableFromVacation()) {
            return;
        }
        TimeOffType timeOffType = timeOffTypeRepository.findByTitle("Vacation");
        individualTimeOff.setReason(timeOffType);
        individualTimeOff.setDisposed(true);
        individualTimeOffRepository.save(individualTimeOff);

    }

    @Override
    public void deleteTimeOff(Long id) {
        IndividualTimeOff individualTimeOff = individualTimeOffRepository.findOne(id);
        individualTimeOffRepository.delete(individualTimeOff);

    }
}
