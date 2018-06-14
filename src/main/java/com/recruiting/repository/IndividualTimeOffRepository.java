package com.recruiting.repository;


import com.recruiting.entity.IndividualTimeOff;
import com.recruiting.entity.TimeOffType;
import com.recruiting.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    Page<IndividualTimeOff> findAllByApprovedFalseOrderByCreatedAtDesc(Pageable pageable);

    Page<IndividualTimeOff> findAllByApprovedFalseAndUser(User user, Pageable pageable);

    Page<IndividualTimeOff> findAllByStartAfterAndUpdatedAtAfter(LocalDateTime startAfter, LocalDateTime updatedAfter, Pageable pageable);

    Page<IndividualTimeOff> findAllByUser(User user, Pageable pageable);


}
