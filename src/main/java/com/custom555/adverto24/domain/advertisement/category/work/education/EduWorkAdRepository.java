package com.custom555.adverto24.domain.advertisement.category.work.education;

import com.custom555.adverto24.domain.advertisement.AdRepository;
import com.custom555.adverto24.domain.advertisement.enums.WorkingTime;

import java.util.Set;

public interface EduWorkAdRepository extends AdRepository<EduWorkAd> {
    Set<EduWorkAd> findAllByWorkingTime(WorkingTime workingTime);

    Set<EduWorkAd> findAllByWorkFromHome(Boolean workFromHome);
}
