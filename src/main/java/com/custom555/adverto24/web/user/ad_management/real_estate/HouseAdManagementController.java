package com.custom555.adverto24.web.user.ad_management.real_estate;

import com.custom555.adverto24.domain.advertisement.category.real_estate.house.HouseAdService;
import com.custom555.adverto24.domain.advertisement.category.real_estate.house.dto.HouseAdSaveDto;
import com.custom555.adverto24.domain.category.CategoryService;
import com.custom555.adverto24.domain.category.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HouseAdManagementController {
    private final String SUBCATEGORY = "domy";
    private final CategoryService categoryService;
    private final HouseAdService adService;

    @GetMapping("/new-ad/nieruchomosci/domy")
    public String getHouseAdForm(Model model){
        addAttributesToModel(model);
        return "forms/ad-form-layout";
    }
    @PostMapping("/new-ad/nieruchomosci/domy")
    public String adHouseAd(HouseAdSaveDto adSaveDto){
        adService.addHouseAd(adSaveDto,SUBCATEGORY);
        return "redirect:/my-account";
    }
    @DeleteMapping("/delete-ad/nieruchomosci/domy/{id}")
    public String deleteHouseAd(@PathVariable long id){
        adService.deleteHouseAd(id);
        return "redirect:/my-account";
    }
    private void addAttributesToModel(Model model){
        List<CategoryDto> categories = categoryService.findAllCategories();
        model.addAttribute("categories",categories);

        HouseAdSaveDto adSaveDto = new HouseAdSaveDto();
        model.addAttribute("adSaveDto",adSaveDto);
        model.addAttribute("title","Dodaj ogłoszenie - "+SUBCATEGORY);
        model.addAttribute("fragment_name","house-form-fragment");
        model.addAttribute("urlPath",  "nieruchomosci/"+SUBCATEGORY);
    }
}
