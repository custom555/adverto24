package com.custom555.adverto24.domain.advertisement.category.home_garden.tool;

import com.custom555.adverto24.domain.advertisement.Ad;
import com.custom555.adverto24.domain.advertisement.enums.State;
import com.custom555.adverto24.domain.advertisement.enums.ToolType;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("TOOL")
@Getter
@Setter
public class ToolAd extends Ad {
    @Enumerated(EnumType.STRING)
    private State state;
    @Enumerated(EnumType.STRING)
    private ToolType toolType;
}

