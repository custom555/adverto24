package com.custom555.adverto24.domain.advertisement.category.work.IT.dto;

import com.custom555.adverto24.domain.advertisement.AdSaveDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItWorkAdSaveDto extends AdSaveDto {
    private Boolean workFromHome;
    private String workingTime;
}
