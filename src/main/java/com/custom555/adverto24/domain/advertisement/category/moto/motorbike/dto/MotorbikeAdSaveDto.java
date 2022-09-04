package com.custom555.adverto24.domain.advertisement.category.moto.motorbike.dto;

import com.custom555.adverto24.domain.advertisement.AdSaveDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotorbikeAdSaveDto extends AdSaveDto {
    private String motorbikeBrand;
    private String state;
    private Integer mileage;
    private Integer enginePower;
    private Integer prodYear;
}
