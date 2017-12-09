package com.recruiting.repository;


import com.recruiting.domain.IndividualTimeOff;
import com.recruiting.domain.TimeOffType;
import com.recruiting.domain.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author sergey, created on 11/17/17.
 */
public interface IndividualTimeOffRepository extends BaseRepository<IndividualTimeOff> {

    List<IndividualTimeOff> findAllByApprovedTrueAndReasonAndUser(TimeOffType timeOffType, User user);

    List<IndividualTimeOff> findAllByApprovedTrueAndUser(User user);

    List<IndividualTimeOff> findAllByStartAfterAndApprovedTrueAndUser(LocalDateTime startAfter, User user);

    List<IndividualTimeOff> findAllByStartAfterAndApprovedFalseAndUser(LocalDateTime startAfter, User user);

    List<IndividualTimeOff> findAllByStartAfterAndApprovedNullAndUser(LocalDateTime startAfter, User user);
}
