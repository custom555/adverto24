package com.custom555.adverto24.web.user.ad_management.home_garden;

import com.custom555.adverto24.domain.advertisement.category.home_garden.furniture.FurnitureAdService;
import com.custom555.adverto24.domain.advertisement.category.home_garden.furniture.dto.FurnitureAdSaveDto;
import com.custom555.adverto24.domain.advertisement.enums.FurnitureType;
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
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class FurnitureAdManagementController {
    private final String SUBCATEGORY = "meble";
    private final CategoryService categoryService;
    private final FurnitureAdService adService;

    @GetMapping("/new-ad/dom_ogrod/meble")
    public String getFurnitureAdForm(Model model){
        addAttributesToModel(model);
        return "forms/ad-form-layout";
    }
    @PostMapping("/new-ad/dom_ogrod/meble")
    public String addFurnitureAd(FurnitureAdSaveDto adSaveDto){
        adService.addFurnitureAd(adSaveDto,SUBCATEGORY);
        return "redirect:/my-account";
    }
    @DeleteMapping("/delete-ad/dom_ogrod/meble/{id}")
    public String deleteFurnitureAd(@PathVariable long id){
        adService.deleteFurnitureAd(id);
        return "redirect:/my-account";
    }
    private void addAttributesToModel(Model model){
        List<CategoryDto> categories = categoryService.findAllCategories();
        model.addAttribute("categories",categories);

        List<State> stateList = Arrays.stream(State.values()).collect(Collectors.toList());
        model.addAttribute("stateList",stateList);

        List<FurnitureType> typeList = Arrays.stream(FurnitureType.values()).collect(Collectors.toList());
        model.addAttribute("typeList",typeList);

        FurnitureAdSaveDto adSaveDto = new FurnitureAdSaveDto();
        model.addAttribute("adSaveDto",adSaveDto);
        model.addAttribute("title","Dodaj ogłoszenie - "+SUBCATEGORY);
        model.addAttribute("fragment_name","furniture-form-fragment");
        model.addAttribute("urlPath",  "dom_ogrod/"+SUBCATEGORY);
    }
}
