package com.custom555.adverto24.web.user.ad_management.work;

import com.custom555.adverto24.domain.advertisement.category.work.education.EduWorkAdService;
import com.custom555.adverto24.domain.advertisement.category.work.education.dto.EduWorkAdSaveDto;
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
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class EduWorkAdManagementController {
    private final String SUBCATEGORY = "edukacja";
    private final CategoryService categoryService;
    private final EduWorkAdService adService;

    @GetMapping("/new-ad/praca/edukacja")
    public String getEduWorkAdForm(Model model){
        addAttributesToModel(model);
        return "forms/ad-form-layout";
    }
    @PostMapping("/new-ad/praca/edukacja")
    public String adEduWorkAd(EduWorkAdSaveDto adSaveDto){
        adService.addEduWorkAd(adSaveDto,SUBCATEGORY);
        return "redirect:/my-account";
    }
    @DeleteMapping("/delete-ad/praca/edukacja/{id}")
    public String deleteEduWorkAd(@PathVariable long id){
        adService.deleteEduWorkAd(id);
        return "redirect:/my-account";
    }
    private void addAttributesToModel(Model model){
        List<CategoryDto> categories = categoryService.findAllCategories();
        model.addAttribute("categories",categories);
        List<WorkingTime> workingTimeList = Arrays.stream(WorkingTime.values()).collect(Collectors.toList());
        model.addAttribute("workingTimeList",workingTimeList);

        EduWorkAdSaveDto adSaveDto = new EduWorkAdSaveDto();
        model.addAttribute("adSaveDto",adSaveDto);
        model.addAttribute("title","Dodaj ogłoszenie - "+SUBCATEGORY);
        model.addAttribute("fragment_name","work-form-fragment");
        model.addAttribute("urlPath",  "praca/"+SUBCATEGORY);
    }
}
