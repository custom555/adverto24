package com.custom555.adverto24.web.advertisement.fashion;

import com.custom555.adverto24.domain.advertisement.category.fashion.men.MensClothesAdService;
import com.custom555.adverto24.domain.advertisement.category.fashion.men.dto.MensClothesAdDto;
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
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/moda/ubrania_meskie")
public class MensClothesAdController {

    private final MensClothesAdService adService;
    private final UserService userService;

    @GetMapping
    public String getMensClothesAdList(Model model){
        List<MensClothesAdDto> mensClothesAds = adService.findAllMensClothesAds();
        model.addAttribute("advertisements",mensClothesAds);
        addConstAttributesToModel(model);
        return "listings/advertisement-listing";
    }

    @GetMapping("/search")
    public String searchForSelectedMensClothesAds(
            @RequestParam(value="state",required = false) State state,
            @RequestParam(value="size",required = false) Size size,
            @RequestParam(value="brand",required = false) FashionBrand brand,
            @RequestParam(value="priceRangeBottom",required = false) Double priceRangeBottom,
            @RequestParam(value="priceRangeTop",required = false) Double priceRangeTop,
            Model model
    ){
        List<MensClothesAdDto> mensClothesAds = adService.findMensClothesAdsByParams(state,size,brand,priceRangeBottom,priceRangeTop);
        model.addAttribute("advertisements",mensClothesAds);
        addConstAttributesToModel(model);

        return "listings/advertisement-listing";
    }
    @GetMapping("/{id}")
    public String getMensClothesAd(@PathVariable long id, Model model){
        Optional<MensClothesAdDto> optionalMensClothesAd = adService.findMensClothesAdById(id);
        if(optionalMensClothesAd.isPresent()){
            MensClothesAdDto mensClothesAd = optionalMensClothesAd.get();
            Optional<UserRegistrationDto> optionalUser = userService.findUserById(mensClothesAd.getOwnerId());
            optionalUser.ifPresent(user -> model.addAttribute("owner",user));
            model.addAttribute("advertisement",mensClothesAd);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "ad-details";
    }
    private void addConstAttributesToModel(Model model){
        List<State> stateList = Arrays.stream(State.values()).collect(Collectors.toList());
        model.addAttribute("stateList",stateList);

        List<Size> sizeList = Arrays.stream(Size.values()).collect(Collectors.toList());
        model.addAttribute("sizeList",sizeList);

        List<FashionBrand> brandList = Arrays.stream(FashionBrand.values()).collect(Collectors.toList());
        model.addAttribute("brandList",brandList);

        model.addAttribute("title","Ubrania męskie");
        model.addAttribute("fragment_name","clothes-search-bar");
        model.addAttribute("url","moda/ubrania_meskie");
    }
}
