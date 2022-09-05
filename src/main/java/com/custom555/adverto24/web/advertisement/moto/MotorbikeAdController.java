package com.custom555.adverto24.web.advertisement.moto;

import com.custom555.adverto24.domain.advertisement.category.moto.motorbike.MotorbikeAdService;
import com.custom555.adverto24.domain.advertisement.category.moto.motorbike.dto.MotorbikeAdDto;
import com.custom555.adverto24.domain.advertisement.enums.MotorbikeBrand;
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
@RequestMapping("/motoryzacja/motocykle")
public class MotorbikeAdController {
    private final MotorbikeAdService adService;
    private final UserService userService;

    @GetMapping
    public String getMotorbikeAdList(Model model){
        List<MotorbikeAdDto> motorbikeAds = adService.findAllMotorbikeAds();
        model.addAttribute("advertisements",motorbikeAds);
        addConstAttributesToModel(model);

        return "listings/advertisement-listing";
    }

    @GetMapping("/search")
    public String searchForSelectedCarAds(
            @RequestParam(value="state",required = false) State state,
            @RequestParam(value="brand",required = false) MotorbikeBrand brand,
            @RequestParam(value="prodYear",required = false) Integer prodYear,
            @RequestParam(value="priceRangeBottom",required = false) Double priceRangeBottom,
            @RequestParam(value="priceRangeTop",required = false) Double priceRangeTop,
            @RequestParam(value="mileageBottom",required = false) Integer mileageBottom,
            @RequestParam(value="mileageTop",required = false) Integer mileageTop,
            @RequestParam(value="enginePowerBottom",required = false) Integer enginePowerBottom,
            @RequestParam(value="enginePowerTop",required = false) Integer enginePowerTop,
            Model model
    ){
        List<MotorbikeAdDto> motorbikeAds = adService.findMotorbikeAdsByParams(state,
                brand,
                prodYear,
                priceRangeBottom,
                priceRangeTop,
                mileageBottom,
                mileageTop,
                enginePowerBottom,
                enginePowerTop);

        model.addAttribute("advertisements",motorbikeAds);
        addConstAttributesToModel(model);
        return "listings/advertisement-listing";
    }
    @GetMapping("/{id}")
    public String getMotorbikeAd(@PathVariable long id, Model model){
        Optional<MotorbikeAdDto> optionalMotorbikeAd = adService.findMotorbikeAdById(id);
        if(optionalMotorbikeAd.isPresent()){
            MotorbikeAdDto motorbikeAd = optionalMotorbikeAd.get();
            Optional<UserRegistrationDto> optionalUser = userService.findUserById(motorbikeAd.getOwnerId());
            optionalUser.ifPresent(user -> model.addAttribute("owner",user));
            model.addAttribute("advertisement",motorbikeAd);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "ad-details";
    }
    private void addConstAttributesToModel(Model model){
        List<State> stateList = Arrays.stream(State.values()).collect(Collectors.toList());
        model.addAttribute("stateList",stateList);

        List<MotorbikeBrand> brandList = Arrays.stream(MotorbikeBrand.values()).collect(Collectors.toList());
        model.addAttribute("brandList",brandList);

        model.addAttribute("title","Motocykle");
        model.addAttribute("fragment_name","moto-search-bar");
        model.addAttribute("url","motoryzacja/motocykle");
    }
}
