package com.custom555.adverto24.domain.advertisement.category.real_estate.house;

import com.custom555.adverto24.domain.advertisement.Ad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("HOUSE")
@Getter
@Setter
public class HouseAd extends Ad {
    private Integer area;
    private Integer floorCount;

}
