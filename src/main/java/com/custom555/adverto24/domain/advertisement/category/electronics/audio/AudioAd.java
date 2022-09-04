package com.custom555.adverto24.domain.advertisement.category.electronics.audio;

import com.custom555.adverto24.domain.advertisement.Ad;
import com.custom555.adverto24.domain.advertisement.enums.State;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("AUDIO")
@Getter
@Setter
public class AudioAd extends Ad {
    @Enumerated(EnumType.STRING)
    private State state;

}
