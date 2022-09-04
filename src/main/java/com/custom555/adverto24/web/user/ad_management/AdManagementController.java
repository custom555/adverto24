package com.custom555.adverto24.web.user.ad_management;

import com.custom555.adverto24.domain.category.CategoryService;
import com.custom555.adverto24.domain.category.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdManagementController {
    private final CategoryService categoryService;

    @GetMapping("/new-ad")
    public String findAllCategories(Model model){
        List<CategoryDto> categories = categoryService.findAllCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("title","Dodaj ogłoszenie");
        return "forms/ad-form-layout";
    }
}
