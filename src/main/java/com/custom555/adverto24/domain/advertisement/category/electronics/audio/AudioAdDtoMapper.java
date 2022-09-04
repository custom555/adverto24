package com.custom555.adverto24.domain.advertisement.category.electronics.audio;

import com.custom555.adverto24.domain.advertisement.AdDtoMapper;
import com.custom555.adverto24.domain.advertisement.category.electronics.audio.dto.AudioAdDto;
import com.custom555.adverto24.domain.advertisement.enums.State;

public class AudioAdDtoMapper {
    public static AudioAdDto toDto(AudioAd audioAd){
        AudioAdDto dto = (AudioAdDto)AdDtoMapper.toDto(audioAd,new AudioAdDto());
        State state = audioAd.getState();
        if(state != null){
            dto.setState(state.toString());
        }
        return dto;
    }
}
