package com.custom555.adverto24.web.user.ad_management.fashion;

import com.custom555.adverto24.domain.advertisement.category.fashion.men.dto.MensClothesAdSaveDto;
import com.custom555.adverto24.domain.advertisement.category.fashion.women.WomenClothesAdService;
import com.custom555.adverto24.domain.advertisement.category.fashion.women.dto.WomenClothesAdSaveDto;
import com.custom555.adverto24.domain.advertisement.enums.FashionBrand;
import com.custom555.adverto24.domain.advertisement.enums.Size;
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
public class WomenClothesAdManagementController {
    private final String SUBCATEGORY = "ubrania_damskie";
    private final CategoryService categoryService;
    private final WomenClothesAdService adService;

    @GetMapping("/new-ad/moda/ubrania_damskie")
    public String getWomenClothesAdForm(Model model){
        addAttributesToModel(model);
        return "forms/ad-form-layout";
    }
    @PostMapping("/new-ad/moda/ubrania_damskie")
    public String addWomenClothesAd(WomenClothesAdSaveDto adSaveDto){
        adService.addWomenClothesAd(adSaveDto,SUBCATEGORY);
        return "redirect:/my-account";
    }
    @DeleteMapping("/delete-ad/moda/ubrania_damskie/{id}")
    public String deleteWomenClothesAd(@PathVariable long id){
        adService.deleteWomenClothesAd(id);
        return "redirect:/my-account";
    }
    private void addAttributesToModel(Model model){
        List<CategoryDto> categories = categoryService.findAllCategories();
        model.addAttribute("categories",categories);

        List<State> stateList = Arrays.stream(State.values()).collect(Collectors.toList());
        model.addAttribute("stateList",stateList);

        List<Size> sizeList = Arrays.stream(Size.values()).collect(Collectors.toList());
        model.addAttribute("sizeList",sizeList);

        List<FashionBrand> brandList = Arrays.stream(FashionBrand.values()).collect(Collectors.toList());
        model.addAttribute("brandList",brandList);

        MensClothesAdSaveDto adSaveDto = new MensClothesAdSaveDto();
        model.addAttribute("adSaveDto",adSaveDto);
        model.addAttribute("title","Dodaj ogłoszenie - "+SUBCATEGORY);
        model.addAttribute("fragment_name","clothes-form-fragment");
        model.addAttribute("urlPath",  "moda/"+SUBCATEGORY);
    }
}
