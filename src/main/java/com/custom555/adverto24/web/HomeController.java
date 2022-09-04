package com.custom555.adverto24.web;

import com.custom555.adverto24.domain.advertisement.AdDto;
import com.custom555.adverto24.domain.advertisement.AdService;
import com.custom555.adverto24.domain.category.CategoryService;
import com.custom555.adverto24.domain.category.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final CategoryService categoryService;
    private final AdService adService;
    @GetMapping("/")
    public String home(Model model){
        List<CategoryDto> categories = categoryService.findAllCategories();
        model.addAttribute("categories",categories);

        List<AdDto> promotedAds = adService.findAllPromotedAds();
        model.addAttribute("advertisements",promotedAds);
        return "listings/main-listing";
    }
}
