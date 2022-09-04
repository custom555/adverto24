package com.custom555.adverto24.domain.advertisement.category.work.IT;

import com.custom555.adverto24.domain.advertisement.AdRepository;
import com.custom555.adverto24.domain.advertisement.enums.WorkingTime;

import java.util.Set;

public interface ItWorkAdRepository extends AdRepository<ItWorkAd> {
    Set<ItWorkAd> findAllByWorkFromHome(Boolean workFromHome);

    Set<ItWorkAd> findAllByWorkingTime(WorkingTime workingTime);
}
