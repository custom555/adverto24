package com.custom555.adverto24.web.user.ad_management.audio;

import com.custom555.adverto24.domain.advertisement.category.electronics.telephone.TelephoneAdService;
import com.custom555.adverto24.domain.advertisement.category.electronics.telephone.dto.TelephoneAdSaveDto;
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
public class TelephoneAdManagementController {
    private final String SUBCATEGORY = "telefony";
    private final CategoryService categoryService;
    private final TelephoneAdService adService;

    @GetMapping("/new-ad/elektronika/telefony")
    public String getTelephoneAdForm(Model model){
        addAttributesToModel(model);
        return "forms/ad-form-layout";
    }
    @PostMapping("/new-ad/elektronika/telefony")
    public String addTelephoneAd(TelephoneAdSaveDto adSaveDto){
        adService.addTelephoneAd(adSaveDto,SUBCATEGORY);
        return "redirect:/my-account";
    }
    @DeleteMapping("/delete-ad/elektronika/telefony/{id}")
    public String deleteTelephoneAd(@PathVariable long id){
        adService.deleteTelephoneAd(id);
        return "redirect:/my-account";
    }
    private void addAttributesToModel(Model model){
        List<CategoryDto> categories = categoryService.findAllCategories();
        model.addAttribute("categories",categories);

        List<State> stateList = Arrays.stream(State.values()).collect(Collectors.toList());
        model.addAttribute("stateList",stateList);

        TelephoneAdSaveDto adSaveDto = new TelephoneAdSaveDto();
        model.addAttribute("adSaveDto",adSaveDto);
        model.addAttribute("title","Dodaj ogłoszenie - "+SUBCATEGORY);
        model.addAttribute("fragment_name","electronics-form-fragment");
        model.addAttribute("urlPath",  "elektronika/"+SUBCATEGORY);
    }
}
