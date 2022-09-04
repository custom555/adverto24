package com.custom555.adverto24.domain.advertisement.category.work.education.dto;

import com.custom555.adverto24.domain.advertisement.AdSaveDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EduWorkAdSaveDto extends AdSaveDto {
    private Boolean workFromHome;
    private String workingTime;
}
