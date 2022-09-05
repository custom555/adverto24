package com.custom555.adverto24.web.advertisement.audio;

import com.custom555.adverto24.domain.advertisement.category.electronics.audio.AudioAdService;
import com.custom555.adverto24.domain.advertisement.category.electronics.audio.dto.AudioAdDto;
import com.custom555.adverto24.domain.advertisement.enums.State;
import com.custom555.adverto24.domain.user.UserService;
import com.custom555.adverto24.domain.user.dto.UserRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/elektronika/sprzet_audio")
public class AudioAdController {
    private final AudioAdService adService;
    private final UserService userService;

    @GetMapping
    public String getAllAudioAds(Model model){
        List<AudioAdDto> audioAds = adService.findAllAudioAds();
        model.addAttribute("advertisements",audioAds);
        addConstAttributesToModel(model);
        return "listings/advertisement-listing";
    }
    @GetMapping("/search")
    public String searchForSelectedAudioAds(
            @RequestParam(value="state",required = false) State state,
            @RequestParam(value="priceRangeBottom",required = false) Double priceRangeBottom,
            @RequestParam(value="priceRangeTop",required = false) Double priceRangeTop,
            Model model
    ){
        List<AudioAdDto> audioAds = adService.findAudioAdsByParams(state,priceRangeBottom,priceRangeTop);
        model.addAttribute("advertisements",audioAds);
        addConstAttributesToModel(model);

        return "listings/advertisement-listing";
    }
    @GetMapping("/{id}")
    public String getAudioAd(@PathVariable long id,Model model){
        Optional<AudioAdDto> optionalAudioAd = adService.findAudioAdById(id);
        if(optionalAudioAd.isPresent()){
            AudioAdDto audioAd = optionalAudioAd.get();
            Optional<UserRegistrationDto> optionalUser = userService.findUserById(audioAd.getOwnerId());
            optionalUser.ifPresent(user -> model.addAttribute("owner",user));
            model.addAttribute("advertisement",audioAd);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "ad-details";
    }
    private void addConstAttributesToModel(Model model){
        List<State> stateList = Arrays.stream(State.values()).collect(Collectors.toList());
        model.addAttribute("stateList",stateList);
        model.addAttribute("title","Sprzęt Audio");
        model.addAttribute("fragment_name","electronics-search-bar");
        model.addAttribute("url","elektronika/sprzet_audio");
    }
}
