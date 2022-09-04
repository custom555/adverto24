package com.custom555.adverto24.web.user.ad_management.real_estate;

import com.custom555.adverto24.domain.advertisement.category.real_estate.flat.FlatAdService;
import com.custom555.adverto24.domain.advertisement.category.real_estate.flat.dto.FlatAdSaveDto;
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
public class FlatAdManagementController {
    private final String SUBCATEGORY = "mieszkania";
    private final CategoryService categoryService;
    private final FlatAdService adService;

    @GetMapping("/new-ad/nieruchomosci/mieszkania")
    public String getFlatAdForm(Model model){
        addAttributesToModel(model);
        return "forms/ad-form-layout";
    }
    @PostMapping("/new-ad/nieruchomosci/mieszkania")
    public String adFlatAd(FlatAdSaveDto adSaveDto){
        adService.addFlatAd(adSaveDto,SUBCATEGORY);
        return "redirect:/my-account";
    }
    @DeleteMapping("/delete-ad/nieruchomosci/mieszkania/{id}")
    public String deleteFlatAd(@PathVariable long id){
        adService.deleteFlatAd(id);
        return "redirect:/my-account";
    }
    private void addAttributesToModel(Model model){
        List<CategoryDto> categories = categoryService.findAllCategories();
        model.addAttribute("categories",categories);

        FlatAdSaveDto adSaveDto = new FlatAdSaveDto();
        model.addAttribute("adSaveDto",adSaveDto);
        model.addAttribute("title","Dodaj ogłoszenie - "+SUBCATEGORY);
        model.addAttribute("fragment_name","flat-form-fragment");
        model.addAttribute("urlPath",  "nieruchomosci/"+SUBCATEGORY);
    }
}
