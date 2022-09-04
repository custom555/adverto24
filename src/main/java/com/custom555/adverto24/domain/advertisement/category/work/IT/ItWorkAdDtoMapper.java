package com.custom555.adverto24.domain.advertisement.category.work.IT;

import com.custom555.adverto24.domain.advertisement.AdDtoMapper;
import com.custom555.adverto24.domain.advertisement.category.work.IT.dto.ItWorkAdDto;
import com.custom555.adverto24.domain.advertisement.enums.WorkingTime;

public class ItWorkAdDtoMapper {

    public static ItWorkAdDto toDto(ItWorkAd itWorkAd) {
        ItWorkAdDto dto = (ItWorkAdDto) AdDtoMapper.toDto(itWorkAd, new ItWorkAdDto());
        WorkingTime workingTime = itWorkAd.getWorkingTime();
        if(workingTime != null){
            dto.setWorkingTime(workingTime.toString());
        }
        dto.setWorkFromHome(itWorkAd.getWorkFromHome());
        return dto;
    }
}
