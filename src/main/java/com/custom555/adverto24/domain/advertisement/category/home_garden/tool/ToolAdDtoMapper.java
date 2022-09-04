package com.custom555.adverto24.domain.advertisement.category.home_garden.tool;

import com.custom555.adverto24.domain.advertisement.AdDtoMapper;
import com.custom555.adverto24.domain.advertisement.category.home_garden.tool.dto.ToolAdDto;
import com.custom555.adverto24.domain.advertisement.enums.State;
import com.custom555.adverto24.domain.advertisement.enums.ToolType;

public class ToolAdDtoMapper {
    public static ToolAdDto toDto(ToolAd toolAd) {
        ToolAdDto dto = (ToolAdDto) AdDtoMapper.toDto(toolAd, new ToolAdDto());
        State state = toolAd.getState();
        if(state != null){
            dto.setState(state.toString());
        }
        ToolType toolType = toolAd.getToolType();
        if(toolType != null){
            dto.setToolType(toolType.toString());
        }
        return dto;
    }
}
