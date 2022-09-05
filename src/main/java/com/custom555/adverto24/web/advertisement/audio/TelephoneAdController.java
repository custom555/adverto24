package com.custom555.adverto24.web.advertisement.audio;

import com.custom555.adverto24.domain.advertisement.category.electronics.telephone.TelephoneAdService;
import com.custom555.adverto24.domain.advertisement.category.electronics.telephone.dto.TelephoneAdDto;
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
@RequestMapping("/elektronika/telefony")
public class TelephoneAdController {
    private final TelephoneAdService adService;
    private final UserService userService;

    @GetMapping
    public String getTelephoneAdList(Model model){
        List<TelephoneAdDto> telephoneAds = adService.findAllTelephoneAds();
        model.addAttribute("advertisements",telephoneAds);
        addConstAttributesToModel(model);
        return "listings/advertisement-listing";
    }

    @GetMapping("/search")
    public String searchForSelectedTelephoneAds(
            @RequestParam(value="state",required = false) State state,
            @RequestParam(value="priceRangeBottom",required = false) Double priceRangeBottom,
            @RequestParam(value="priceRangeTop",required = false) Double priceRangeTop,
            Model model
    ){
        List<TelephoneAdDto> telephoneAds = adService.findTelephoneAdsByParams(state,priceRangeBottom,priceRangeTop);
        model.addAttribute("advertisements",telephoneAds);
        addConstAttributesToModel(model);

        return "listings/advertisement-listing";
    }
    @GetMapping("/{id}")
    public String getTelephoneAd(@PathVariable long id, Model model){
        Optional<TelephoneAdDto> optionalTelephoneAd = adService.findTelephoneAdById(id);
        if(optionalTelephoneAd.isPresent()){
            TelephoneAdDto telephoneAd = optionalTelephoneAd.get();
            Optional<UserRegistrationDto> optionalUser = userService.findUserById(telephoneAd.getOwnerId());
            optionalUser.ifPresent(user -> model.addAttribute("owner",user));
            model.addAttribute("advertisement",telephoneAd);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "ad-details";
    }

    private void addConstAttributesToModel(Model model){
        List<State> stateList = Arrays.stream(State.values()).collect(Collectors.toList());
        model.addAttribute("stateList",stateList);
        model.addAttribute("title","Telefony");
        model.addAttribute("fragment_name","electronics-search-bar");
        model.addAttribute("url","elektronika/telefony");
    }
}
