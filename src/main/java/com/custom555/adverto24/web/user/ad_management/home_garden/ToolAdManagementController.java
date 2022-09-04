package com.custom555.adverto24.web.user.ad_management.home_garden;

import com.custom555.adverto24.domain.advertisement.category.home_garden.tool.ToolAdService;
import com.custom555.adverto24.domain.advertisement.category.home_garden.tool.dto.ToolAdSaveDto;
import com.custom555.adverto24.domain.advertisement.enums.State;
import com.custom555.adverto24.domain.advertisement.enums.ToolType;
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
public class ToolAdManagementController {
    private final String SUBCATEGORY = "narzedzia";
    private final CategoryService categoryService;
    private final ToolAdService adService;

    @GetMapping("/new-ad/dom_ogrod/narzedzia")
    public String getToolAdForm(Model model){
        addAttributesToModel(model);
        return "forms/ad-form-layout";
    }
    @PostMapping("/new-ad/dom_ogrod/narzedzia")
    public String adToolAd(ToolAdSaveDto adSaveDto){
        adService.addToolAd(adSaveDto,SUBCATEGORY);
        return "redirect:/my-account";
    }
    @DeleteMapping("/delete-ad/dom_ogrod/narzedzia/{id}")
    public String deleteToolAd(@PathVariable long id){
        adService.deleteToolAd(id);
        return "redirect:/my-account";
    }
    private void addAttributesToModel(Model model){
        List<CategoryDto> categories = categoryService.findAllCategories();
        model.addAttribute("categories",categories);

        List<State> stateList = Arrays.stream(State.values()).toList();
        model.addAttribute("stateList",stateList);

        List<ToolType> typeList = Arrays.stream(ToolType.values()).toList();
        model.addAttribute("typeList",typeList);

        ToolAdSaveDto adSaveDto = new ToolAdSaveDto();
        model.addAttribute("adSaveDto",adSaveDto);
        model.addAttribute("title","Dodaj ogłoszenie - narzędzia");
        model.addAttribute("fragment_name","tool-form-fragment");
        model.addAttribute("urlPath",  "dom_ogrod/"+SUBCATEGORY);
    }
}
