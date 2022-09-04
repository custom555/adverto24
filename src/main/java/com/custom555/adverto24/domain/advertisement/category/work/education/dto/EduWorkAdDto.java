package com.custom555.adverto24.domain.advertisement.category.work.education.dto;

import com.custom555.adverto24.domain.advertisement.AdDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EduWorkAdDto extends AdDto {
    private Boolean workFromHome;
    private String workingTime;
}
