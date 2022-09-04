package com.custom555.adverto24.domain.advertisement.category.moto.motorbike;

import com.custom555.adverto24.domain.advertisement.Ad;
import com.custom555.adverto24.domain.advertisement.enums.MotorbikeBrand;
import com.custom555.adverto24.domain.advertisement.enums.State;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("MOTO")
@Getter
@Setter
public class MotorbikeAd extends Ad {
    @Enumerated(EnumType.STRING)
    private MotorbikeBrand motorbikeBrand;
    @Enumerated(EnumType.STRING)
    private State state;
    private Integer mileage;
    private Integer enginePower;
    private Integer prodYear;
}
