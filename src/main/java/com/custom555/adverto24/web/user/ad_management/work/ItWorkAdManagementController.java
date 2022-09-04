package com.custom555.adverto24.web.user.ad_management.work;

import com.custom555.adverto24.domain.advertisement.category.work.IT.ItWorkAdService;
import com.custom555.adverto24.domain.advertisement.category.work.IT.dto.ItWorkAdSaveDto;
import com.custom555.adverto24.domain.advertisement.enums.WorkingTime;
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
public class ItWorkAdManagementController {
    private final String SUBCATEGORY = "it";
    private final CategoryService categoryService;
    private final ItWorkAdService adService;

    @GetMapping("/new-ad/praca/it")
    public String getItWorkAdForm(Model model){
        addAttributesToModel(model);
        return "forms/ad-form-layout";
    }
    @PostMapping("/new-ad/praca/it")
    public String adItWorkAd(ItWorkAdSaveDto adSaveDto){
        adService.addItWorkAd(adSaveDto,SUBCATEGORY);
        return "redirect:/my-account";
    }
    @DeleteMapping("/delete-ad/praca/it/{id}")
    public String deleteItWorkAd(@PathVariable long id){
        adService.deleteItWorkAd(id);
        return "redirect:/my-account";
    }
    private void addAttributesToModel(Model model){
        List<CategoryDto> categories = categoryService.findAllCategories();
        model.addAttribute("categories",categories);
        List<WorkingTime> workingTimeList = Arrays.stream(WorkingTime.values()).toList();
        model.addAttribute("workingTimeList",workingTimeList);

        ItWorkAdSaveDto adSaveDto = new ItWorkAdSaveDto();
        model.addAttribute("adSaveDto",adSaveDto);
        model.addAttribute("title","Dodaj ogłoszenie - "+SUBCATEGORY);
        model.addAttribute("fragment_name","work-form-fragment");
        model.addAttribute("urlPath",  "praca/"+SUBCATEGORY);
    }
}
