package com.custom555.adverto24.web.advertisement.edu;

import com.custom555.adverto24.domain.advertisement.category.work.education.EduWorkAd;
import com.custom555.adverto24.domain.advertisement.category.work.education.EduWorkAdService;
import com.custom555.adverto24.domain.advertisement.category.work.education.dto.EduWorkAdDto;
import com.custom555.adverto24.domain.advertisement.enums.WorkingTime;
import com.custom555.adverto24.domain.user.UserService;
import com.custom555.adverto24.domain.user.dto.UserRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/praca/edukacja")
public class EduWorkAdController {
    private final EduWorkAdService adService;
    private final UserService userService;

    @GetMapping
    public String getEduWorkAdList(Model model){
        List<EduWorkAdDto> eduWorkAds = adService.findAllEduWorkAds();
        model.addAttribute("advertisements",eduWorkAds);
        addConstAttributesToModel(model);
        return "listings/advertisement-listing";
    }
    @GetMapping("/search")
    public String searchForSelectedItWorkAds(
            @RequestParam(value="workingTime",required = false) WorkingTime workingTime,
            @RequestParam(value="workFromHome",required = false) Boolean workFromHome,
            Model model
    ){
        List<EduWorkAdDto> eduWorkAds = adService.findEduWorkAdsByParams(workingTime,workFromHome);
        model.addAttribute("advertisements",eduWorkAds);
        addConstAttributesToModel(model);

        return "listings/advertisement-listing";
    }
    @GetMapping("/{id}")
    public String getEduWorkAd(@PathVariable long id, Model model){
        Optional<EduWorkAdDto> optionalEduWorkAd = adService.findEduWorkAdById(id);
        if(optionalEduWorkAd.isPresent()){
            EduWorkAdDto eduWorkAd = optionalEduWorkAd.get();
            Optional<UserRegistrationDto> optionalUser = userService.findUserById(eduWorkAd.getOwnerId());
            optionalUser.ifPresent(user -> model.addAttribute("owner",user));
            model.addAttribute("advertisement",eduWorkAd);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "ad-details";
    }
    private void addConstAttributesToModel(Model model){
        List<WorkingTime> workingTimeList = Arrays.stream(WorkingTime.values()).toList();
        model.addAttribute("workingTimeList",workingTimeList);
        model.addAttribute("title","Edukacja");
        model.addAttribute("fragment_name","work-search-bar");
        model.addAttribute("url","praca/edukacja");
    }
}
