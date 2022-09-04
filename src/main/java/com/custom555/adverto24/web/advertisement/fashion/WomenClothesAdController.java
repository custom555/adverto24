package com.custom555.adverto24.web.advertisement.fashion;

import com.custom555.adverto24.domain.advertisement.category.fashion.women.WomenClothesAdService;
import com.custom555.adverto24.domain.advertisement.category.fashion.women.dto.WomenClothesAdDto;
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
@RequestMapping("/moda/ubrania_damskie")
public class WomenClothesAdController {
    private final WomenClothesAdService adService;
    private final UserService userService;

    @GetMapping
    public String getWomenClothesAdList(Model model){
        List<WomenClothesAdDto> womenClothesAds = adService.findAllWomenClothesAds();
        model.addAttribute("advertisements",womenClothesAds);
        addConstAttributesToModel(model);
        return "listings/advertisement-listing";
    }
    @GetMapping("/search")
    public String searchForSelectedWomenClothesAds(
            @RequestParam(value="state",required = false) State state,
            @RequestParam(value="size",required = false) Size size,
            @RequestParam(value="brand",required = false) FashionBrand brand,
            @RequestParam(value="priceRangeBottom",required = false) Double priceRangeBottom,
            @RequestParam(value="priceRangeTop",required = false) Double priceRangeTop,
            Model model
    ){
        List<WomenClothesAdDto> womenClothesAds = adService.findWomenClothesAdsByParams(state,size,brand,priceRangeBottom,priceRangeTop);
        model.addAttribute("advertisements",womenClothesAds);
        addConstAttributesToModel(model);

        return "listings/advertisement-listing";
    }
    @GetMapping("/{id}")
    public String getWomenClothesAd(@PathVariable long id, Model model){
        Optional<WomenClothesAdDto> optionalWomenClothesAd = adService.findWomenClothesAdById(id);
        if(optionalWomenClothesAd.isPresent()){
            WomenClothesAdDto womenClothesAd = optionalWomenClothesAd.get();
            Optional<UserRegistrationDto> optionalUser = userService.findUserById(womenClothesAd.getOwnerId());
            optionalUser.ifPresent(user -> model.addAttribute("owner",user));
            model.addAttribute("advertisement",womenClothesAd);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "ad-details";
    }
    private void addConstAttributesToModel(Model model){
        List<State> stateList = Arrays.stream(State.values()).toList();
        model.addAttribute("stateList",stateList);

        List<Size> sizeList = Arrays.stream(Size.values()).toList();
        model.addAttribute("sizeList",sizeList);

        List<FashionBrand> brandList = Arrays.stream(FashionBrand.values()).toList();
        model.addAttribute("brandList",brandList);

        model.addAttribute("title","Ubrania damskie");
        model.addAttribute("fragment_name","clothes-search-bar");
        model.addAttribute("url","moda/ubrania_damskie");
    }
}
