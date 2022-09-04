package com.custom555.adverto24.domain.advertisement.category.electronics.telephone;

import com.custom555.adverto24.domain.advertisement.Ad;
import com.custom555.adverto24.domain.advertisement.enums.State;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("TEL")
@Getter
@Setter
public class TelephoneAd extends Ad {
    @Enumerated(EnumType.STRING)
    private State state;

}
