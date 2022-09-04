package com.custom555.adverto24.domain.advertisement.category.moto.car;

import com.custom555.adverto24.domain.advertisement.Ad;
import com.custom555.adverto24.domain.advertisement.enums.CarBrand;
import com.custom555.adverto24.domain.advertisement.enums.State;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("CAR")
@Getter
@Setter
public class CarAd extends Ad {
    @Enumerated(EnumType.STRING)
    private CarBrand carBrand;
    @Enumerated(EnumType.STRING)
    private State state;
    private Integer mileage;
    private Integer enginePower;
    private Integer prodYear;
}
