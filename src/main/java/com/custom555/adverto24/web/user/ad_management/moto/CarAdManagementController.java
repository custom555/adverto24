package com.custom555.adverto24.web.user.ad_management.moto;

import com.custom555.adverto24.domain.advertisement.category.moto.car.CarAdService;
import com.custom555.adverto24.domain.advertisement.category.moto.car.dto.CarAdSaveDto;
import com.custom555.adverto24.domain.advertisement.enums.CarBrand;
import com.custom555.adverto24.domain.advertisement.enums.State;
import com.custom555.adverto24.domain.category.CategoryService;
import com.custom555.adverto24.domain.category.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CarAdManagementController {
    private final String SUBCATEGORY = "samochody_osobowe";
    private final CategoryService categoryService;
    private final CarAdService adService;

    @GetMapping("/new-ad/motoryzacja/samochody_osobowe")
    public String getCarAdForm(Model model){
        addAttributesToModel(model);
        return "forms/ad-form-layout";
    }
    @PostMapping("/new-ad/motoryzacja/samochody_osobowe")
    public String adCarAd(CarAdSaveDto adSaveDto){
        adService.addCarAd(adSaveDto,SUBCATEGORY);
        return "redirect:/my-account";
    }
    @DeleteMapping("/delete-ad/motoryzacja/samochody_osobowe/{id}")
    public String deleteCarAd(@PathVariable long id){
        adService.deleteCarAd(id);
        return "redirect:/my-account";
    }
    private void addAttributesToModel(Model model){
        List<CategoryDto> categories = categoryService.findAllCategories();
        model.addAttribute("categories",categories);

        List<State> stateList = Arrays.stream(State.values()).toList();
        model.addAttribute("stateList",stateList);

        List<CarBrand> brandList = Arrays.stream(CarBrand.values()).toList();
        model.addAttribute("brandList",brandList);

        CarAdSaveDto adSaveDto = new CarAdSaveDto();
        model.addAttribute("adSaveDto",adSaveDto);
        model.addAttribute("title","Dodaj ogłoszenie - "+SUBCATEGORY);
        model.addAttribute("fragment_name","car-form-fragment");
        model.addAttribute("urlPath",  "motoryzacja/"+SUBCATEGORY);
    }
}
