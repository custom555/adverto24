package com.custom555.adverto24.web.advertisement.real_estate;

import com.custom555.adverto24.domain.advertisement.category.home_garden.furniture.dto.FurnitureAdDto;
import com.custom555.adverto24.domain.advertisement.category.real_estate.flat.dto.FlatAdDto;
import com.custom555.adverto24.domain.advertisement.category.real_estate.house.HouseAd;
import com.custom555.adverto24.domain.advertisement.category.real_estate.house.HouseAdService;
import com.custom555.adverto24.domain.advertisement.category.real_estate.house.dto.HouseAdDto;
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

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/nieruchomosci/domy")
public class HouseAdController {
    private final HouseAdService adService;
    private final UserService userService;

    @GetMapping
    public String getHouseAdList(Model model){
        List<HouseAdDto> houseAds = adService.findAllHouseAds();
        model.addAttribute("advertisements",houseAds);
        addConstAttributesToModel(model);
        return "listings/advertisement-listing";
    }
    @GetMapping("/search")
    public String searchForSelectedFlatAds(
            @RequestParam(value="floorCountBottom",required = false) Integer floorCountBottom,
            @RequestParam(value="floorCountTop",required = false) Integer floorCountTop,
            @RequestParam(value="areaBottom",required = false) Integer areaBottom,
            @RequestParam(value="areaTop",required = false) Integer areaTop,
            @RequestParam(value="priceRangeBottom",required = false) Double priceRangeBottom,
            @RequestParam(value="priceRangeTop",required = false) Double priceRangeTop,
            Model model
    ){
        List<HouseAdDto> flatAds = adService.findHouseAdsByParams(floorCountBottom,
                                                                  floorCountTop,
                                                                  areaBottom,
                                                                  areaTop,
                                                                  priceRangeBottom,
                                                                  priceRangeTop);
        model.addAttribute("advertisements",flatAds);
        addConstAttributesToModel(model);

        return "listings/advertisement-listing";
    }
    @GetMapping("/{id}")
    public String getHouseAd(@PathVariable long id, Model model){
        Optional<HouseAdDto> optionalHouseAd = adService.findHouseAdById(id);
        if(optionalHouseAd.isPresent()){
            HouseAdDto houseAd = optionalHouseAd.get();
            Optional<UserRegistrationDto> optionalUser = userService.findUserById(houseAd.getOwnerId());
            optionalUser.ifPresent(user -> model.addAttribute("owner",user));
            model.addAttribute("advertisement",houseAd);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "ad-details";
    }
    private void addConstAttributesToModel(Model model){
        model.addAttribute("title","Domy");
        model.addAttribute("fragment_name","real-estate-search-bar");
        model.addAttribute("url","nieruchomosci/domy");
    }

}
