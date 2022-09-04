package com.custom555.adverto24.web.advertisement.real_estate;

import com.custom555.adverto24.domain.advertisement.category.real_estate.flat.FlatAdService;
import com.custom555.adverto24.domain.advertisement.category.real_estate.flat.dto.FlatAdDto;
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
@RequestMapping("/nieruchomosci/mieszkania")
public class FlatAdController {
    private final FlatAdService adService;
    private final UserService userService;

    @GetMapping
    public String getFlatAdList(Model model){
        List<FlatAdDto> flatAds = adService.findAllFlatAds();
        model.addAttribute("advertisements",flatAds);
        addConstAttributesToModel(model);
        return "listings/advertisement-listing";
    }

    @GetMapping("/search")
    public String searchForSelectedFlatAds(
            @RequestParam(value="roomCountBottom",required = false) Integer roomCountBottom,
            @RequestParam(value="roomCountTop",required = false) Integer roomCountTop,
            @RequestParam(value="areaBottom",required = false) Integer areaBottom,
            @RequestParam(value="areaTop",required = false) Integer areaTop,
            @RequestParam(value="priceRangeBottom",required = false) Double priceRangeBottom,
            @RequestParam(value="priceRangeTop",required = false) Double priceRangeTop,
            Model model
    ){
        List<FlatAdDto> flatAds = adService.findFlatAdsByParams(roomCountBottom,
                                                                roomCountTop,
                                                                areaBottom,
                                                                areaTop,
                                                                priceRangeBottom,
                                                                priceRangeTop);
        model.addAttribute("advertisements",flatAds);
        addConstAttributesToModel(model);

        return "listings/advertisement-listing";
    }
    @GetMapping("/{id}")
    public String getFlatAd(@PathVariable long id, Model model){
        Optional<FlatAdDto> optionalFlatAd = adService.findFlatAdById(id);
        if(optionalFlatAd.isPresent()){
            FlatAdDto flatAd = optionalFlatAd.get();
            Optional<UserRegistrationDto> optionalUser = userService.findUserById(flatAd.getOwnerId());
            optionalUser.ifPresent(user -> model.addAttribute("owner",user));
            model.addAttribute("advertisement",flatAd);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "ad-details";
    }
    private void addConstAttributesToModel(Model model){
        model.addAttribute("title","Mieszkania");
        model.addAttribute("fragment_name","real-estate-search-bar");
        model.addAttribute("url","nieruchomosci/mieszkania");
    }
}
