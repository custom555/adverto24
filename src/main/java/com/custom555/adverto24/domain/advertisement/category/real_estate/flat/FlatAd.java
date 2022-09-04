package com.custom555.adverto24.domain.advertisement.category.real_estate.flat;

import com.custom555.adverto24.domain.advertisement.Ad;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FLAT")
@Getter
@Setter
public class FlatAd extends Ad {
    private Integer area;
    private Integer roomCount;
}
