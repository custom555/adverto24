package com.custom555.adverto24.domain.advertisement.category.work.education;

import com.custom555.adverto24.domain.advertisement.AdDtoMapper;
import com.custom555.adverto24.domain.advertisement.category.work.education.dto.EduWorkAdDto;
import com.custom555.adverto24.domain.advertisement.enums.WorkingTime;

public class EduWorkAdDtoMapper {
    public static EduWorkAdDto toDto(EduWorkAd eduWorkAd) {
        EduWorkAdDto dto = (EduWorkAdDto) AdDtoMapper.toDto(eduWorkAd, new EduWorkAdDto());
        WorkingTime workingTime = eduWorkAd.getWorkingTime();
        if(workingTime != null){
            dto.setWorkingTime(workingTime.toString());
        }
        dto.setWorkFromHome(eduWorkAd.getWorkFromHome());
        return dto;
    }
}
