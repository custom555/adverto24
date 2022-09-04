package com.custom555.adverto24.web.advertisement.moto;

import com.custom555.adverto24.domain.advertisement.category.fashion.men.dto.MensClothesAdDto;
import com.custom555.adverto24.domain.advertisement.category.home_garden.tool.dto.ToolAdDto;
import com.custom555.adverto24.domain.advertisement.category.moto.car.CarAdService;
import com.custom555.adverto24.domain.advertisement.category.moto.car.dto.CarAdDto;
import com.custom555.adverto24.domain.advertisement.enums.CarBrand;
import com.custom555.adverto24.domain.advertisement.enums.FashionBrand;
import com.custom555.adverto24.domain.advertisement.enums.Size;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/motoryzacja/samochody_osobowe")
public class CarAdController {
    private final CarAdService adService;
    private final UserService userService;

    @GetMapping
    public String getCarAdList(Model model){
        List<CarAdDto> carAds = adService.findAllCarAds();
        model.addAttribute("advertisements",carAds);
        addConstAttributesToModel(model);
        return "listings/advertisement-listing";
    }
    @GetMapping("/search")
    public String searchForSelectedCarAds(
            @RequestParam(value="state",required = false) State state,
            @RequestParam(value="brand",required = false) CarBrand brand,
            @RequestParam(value="prodYear",required = false) Integer prodYear,
            @RequestParam(value="priceRangeBottom",required = false) Double priceRangeBottom,
            @RequestParam(value="priceRangeTop",required = false) Double priceRangeTop,
            @RequestParam(value="mileageBottom",required = false) Integer mileageBottom,
            @RequestParam(value="mileageTop",required = false) Integer mileageTop,
            @RequestParam(value="enginePowerBottom",required = false) Integer enginePowerBottom,
            @RequestParam(value="enginePowerTop",required = false) Integer enginePowerTop,
            Model model
    ){
        List<CarAdDto> carAds = adService.findCarAdsByParams(state,
                                                             brand,
                                                             prodYear,
                                                             priceRangeBottom,
                                                             priceRangeTop,
                                                             mileageBottom,
                                                             mileageTop,
                                                             enginePowerBottom,
                                                             enginePowerTop);

        model.addAttribute("advertisements",carAds);
        addConstAttributesToModel(model);
        return "listings/advertisement-listing";
    }
    @GetMapping("/{id}")
    public String getCarAd(@PathVariable long id, Model model){
        Optional<CarAdDto> optionalCarAd = adService.findCarAdById(id);
        if(optionalCarAd.isPresent()){
            CarAdDto carAd = optionalCarAd.get();
            Optional<UserRegistrationDto> optionalUser = userService.findUserById(carAd.getOwnerId());
            optionalUser.ifPresent(user -> model.addAttribute("owner",user));
            model.addAttribute("advertisement",carAd);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "ad-details";
    }
    private void addConstAttributesToModel(Model model){
        List<State> stateList = Arrays.stream(State.values()).toList();
        model.addAttribute("stateList",stateList);

        List<CarBrand> brandList = Arrays.stream(CarBrand.values()).toList();
        model.addAttribute("brandList",brandList);

        model.addAttribute("title","Samochody");
        model.addAttribute("fragment_name","moto-search-bar");
        model.addAttribute("url","motoryzacja/samochody_osobowe");
    }

}
