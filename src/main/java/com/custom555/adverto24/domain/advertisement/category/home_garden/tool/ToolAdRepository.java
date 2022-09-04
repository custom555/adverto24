package com.custom555.adverto24.domain.advertisement.category.home_garden.tool;

import com.custom555.adverto24.domain.advertisement.AdRepository;
import com.custom555.adverto24.domain.advertisement.enums.State;
import com.custom555.adverto24.domain.advertisement.enums.ToolType;

import java.util.Set;

public interface ToolAdRepository extends AdRepository<ToolAd> {
    Set<ToolAd> findAllByToolType(ToolType type);
    Set<ToolAd> findAllByState(State state);

}
