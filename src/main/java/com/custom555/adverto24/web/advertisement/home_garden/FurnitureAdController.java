package com.custom555.adverto24.web.advertisement.home_garden;

import com.custom555.adverto24.domain.advertisement.category.home_garden.furniture.FurnitureAdService;
import com.custom555.adverto24.domain.advertisement.category.home_garden.furniture.dto.FurnitureAdDto;
import com.custom555.adverto24.domain.advertisement.enums.FurnitureType;
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
@RequestMapping("/dom_ogrod/meble")
public class FurnitureAdController {
    private final FurnitureAdService adService;
    private final UserService userService;

    @GetMapping
    public String getFurnitureAdList(Model model){
        List<FurnitureAdDto> furnitureAds = adService.findAllFurnitureAds();
        model.addAttribute("advertisements",furnitureAds);
        addConstAttributesToModel(model);
        return "listings/advertisement-listing";
    }

    @GetMapping("/search")
    public String searchForSelectedFurnitureAds(
            @RequestParam(value="state",required = false) State state,
            @RequestParam(value="type",required = false) FurnitureType type,
            @RequestParam(value="priceRangeBottom",required = false) Double priceRangeBottom,
            @RequestParam(value="priceRangeTop",required = false) Double priceRangeTop,
            Model model
    ){
        List<FurnitureAdDto> furnitureAds = adService.findFurnitureAdsByParams(state,type,priceRangeBottom,priceRangeTop);
        model.addAttribute("advertisements",furnitureAds);
        addConstAttributesToModel(model);

        return "listings/advertisement-listing";
    }
    @GetMapping("/{id}")
    public String getFurnitureAd(@PathVariable long id, Model model){
        Optional<FurnitureAdDto> optionalFurnitureAd = adService.findFurnitureAdById(id);
        if(optionalFurnitureAd.isPresent()){
            FurnitureAdDto furnitureAd = optionalFurnitureAd.get();
            Optional<UserRegistrationDto> optionalUser = userService.findUserById(furnitureAd.getOwnerId());
            optionalUser.ifPresent(user -> model.addAttribute("owner",user));
            model.addAttribute("advertisement",furnitureAd);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "ad-details";
    }

    private void addConstAttributesToModel(Model model){
        List<State> stateList = Arrays.stream(State.values()).collect(Collectors.toList());
        model.addAttribute("stateList",stateList);

        List<FurnitureType> typeList = Arrays.stream(FurnitureType.values()).collect(Collectors.toList());
        model.addAttribute("typeList",typeList);

        model.addAttribute("title","Meble");
        model.addAttribute("fragment_name","home-search-bar");
        model.addAttribute("url","dom_ogrod/meble");
    }
}
