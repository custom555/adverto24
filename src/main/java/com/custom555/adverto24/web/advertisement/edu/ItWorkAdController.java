package com.custom555.adverto24.web.advertisement.edu;

import com.custom555.adverto24.domain.advertisement.category.work.IT.ItWorkAd;
import com.custom555.adverto24.domain.advertisement.category.work.IT.ItWorkAdService;
import com.custom555.adverto24.domain.advertisement.category.work.IT.dto.ItWorkAdDto;
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
@RequestMapping("/praca/it")
public class ItWorkAdController {
    private final ItWorkAdService adService;
    private final UserService userService;

    @GetMapping
    public String getItWorkAdList(Model model){
        List<ItWorkAdDto> itWorkAds = adService.findAllItWorkAds();
        model.addAttribute("advertisements",itWorkAds);
        addConstAttributesToModel(model);
        return "listings/advertisement-listing";
    }
    @GetMapping("/search")
    public String searchForSelectedItWorkAds(
            @RequestParam(value="workingTime",required = false) WorkingTime workingTime,
            @RequestParam(value="workFromHome",required = false) Boolean workFromHome,
            Model model
    ){
        List<ItWorkAdDto> itWorkAds = adService.findItWorkAdsByParams(workingTime,workFromHome);
        model.addAttribute("advertisements",itWorkAds);
        addConstAttributesToModel(model);

        return "listings/advertisement-listing";
    }
    @GetMapping("/{id}")
    public String getItWorkAd(@PathVariable long id, Model model){
        Optional<ItWorkAdDto> optionalItWorkAd = adService.findItWorkAdById(id);
        if(optionalItWorkAd.isPresent()){
            ItWorkAdDto itWorkAd = optionalItWorkAd.get();
            Optional<UserRegistrationDto> optionalUser = userService.findUserById(itWorkAd.getOwnerId());
            optionalUser.ifPresent(user -> model.addAttribute("owner",user));
            model.addAttribute("advertisement",itWorkAd);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "ad-details";
    }
    private void addConstAttributesToModel(Model model){
        List<WorkingTime> workingTimeList = Arrays.stream(WorkingTime.values()).toList();
        model.addAttribute("workingTimeList",workingTimeList);
        model.addAttribute("title","IT");
        model.addAttribute("fragment_name","work-search-bar");
        model.addAttribute("url","praca/it");
    }
}
