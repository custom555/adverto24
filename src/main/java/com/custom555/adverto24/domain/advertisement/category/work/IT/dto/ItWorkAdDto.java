package com.custom555.adverto24.domain.advertisement.category.work.IT.dto;

import com.custom555.adverto24.domain.advertisement.AdDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItWorkAdDto extends AdDto {
    private Boolean workFromHome;
    private String workingTime;
}
