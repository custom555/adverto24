package com.custom555.adverto24.web.user.ad_management.moto;

import com.custom555.adverto24.domain.advertisement.category.moto.motorbike.MotorbikeAdService;
import com.custom555.adverto24.domain.advertisement.category.moto.motorbike.dto.MotorbikeAdSaveDto;
import com.custom555.adverto24.domain.advertisement.enums.MotorbikeBrand;
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
public class MotorbikeAdManagementController {
    private final String SUBCATEGORY = "motocykle";
    private final CategoryService categoryService;
    private final MotorbikeAdService adService;

    @GetMapping("/new-ad/motoryzacja/motocykle")
    public String getMotorbikeAdForm(Model model){
        addAttributesToModel(model);
        return "forms/ad-form-layout";
    }
    @PostMapping("/new-ad/motoryzacja/motocykle")
    public String adMotorbikeAd(MotorbikeAdSaveDto adSaveDto){
        adService.addMotorbikeAd(adSaveDto,SUBCATEGORY);
        return "redirect:/my-account";
    }
    @DeleteMapping("/delete-ad/motoryzacja/motocykle/{id}")
    public String deleteMotorbikeAd(@PathVariable long id){
        adService.deleteMotorbikeAd(id);
        return "redirect:/my-account";
    }
    private void addAttributesToModel(Model model){
        List<CategoryDto> categories = categoryService.findAllCategories();
        model.addAttribute("categories",categories);

        List<State> stateList = Arrays.stream(State.values()).toList();
        model.addAttribute("stateList",stateList);

        List<MotorbikeBrand> brandList = Arrays.stream(MotorbikeBrand.values()).toList();
        model.addAttribute("brandList",brandList);

        MotorbikeAdSaveDto adSaveDto = new MotorbikeAdSaveDto();
        model.addAttribute("adSaveDto",adSaveDto);
        model.addAttribute("title","Dodaj ogłoszenie - "+SUBCATEGORY);
        model.addAttribute("fragment_name","motorbike-form-fragment");
        model.addAttribute("urlPath",  "motoryzacja/"+SUBCATEGORY);
    }
}
