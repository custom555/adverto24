package com.custom555.adverto24.domain.advertisement.category.work.education;

import com.custom555.adverto24.domain.advertisement.Ad;
import com.custom555.adverto24.domain.advertisement.enums.WorkingTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("WORK-EDU")
@Getter
@Setter
public class EduWorkAd extends Ad {
    private Boolean workFromHome;
    @Enumerated(EnumType.STRING)
    private WorkingTime workingTime;
}
