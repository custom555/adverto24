package com.custom555.adverto24.web.user.ad_management.fashion;

import com.custom555.adverto24.domain.advertisement.category.fashion.men.MensClothesAdService;
import com.custom555.adverto24.domain.advertisement.category.fashion.men.dto.MensClothesAdSaveDto;
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

@Controller
@RequiredArgsConstructor
public class MensClothesAdManagementController {
    private final String SUBCATEGORY = "ubrania_meskie";
    private final CategoryService categoryService;
    private final MensClothesAdService adService;

    @GetMapping("/new-ad/moda/ubrania_meskie")
    public String getMensClothesAdForm(Model model){
        addAttributesToModel(model);
        return "forms/ad-form-layout";
    }
    @PostMapping("/new-ad/moda/ubrania_meskie")
    public String addMensClothesAd(MensClothesAdSaveDto adSaveDto){
        adService.addMensClothesAd(adSaveDto,SUBCATEGORY);
        return "redirect:/my-account";
    }
    @DeleteMapping("/delete-ad/moda/ubrania_meskie/{id}")
    public String deleteMensClothesAd(@PathVariable long id){
        adService.deleteMensClothesAd(id);
        return "redirect:/my-account";
    }
    private void addAttributesToModel(Model model){
        List<CategoryDto> categories = categoryService.findAllCategories();
        model.addAttribute("categories",categories);

        List<State> stateList = Arrays.stream(State.values()).toList();
        model.addAttribute("stateList",stateList);

        List<Size> sizeList = Arrays.stream(Size.values()).toList();
        model.addAttribute("sizeList",sizeList);

        List<FashionBrand> brandList = Arrays.stream(FashionBrand.values()).toList();
        model.addAttribute("brandList",brandList);

        MensClothesAdSaveDto adSaveDto = new MensClothesAdSaveDto();
        model.addAttribute("adSaveDto",adSaveDto);
        model.addAttribute("title","Dodaj ogłoszenie - "+SUBCATEGORY);
        model.addAttribute("fragment_name","clothes-form-fragment");
        model.addAttribute("urlPath",  "moda/"+SUBCATEGORY);
    }
}
