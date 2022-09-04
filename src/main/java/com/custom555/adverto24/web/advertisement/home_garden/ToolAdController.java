package com.custom555.adverto24.web.advertisement.home_garden;

import com.custom555.adverto24.domain.advertisement.category.home_garden.tool.ToolAdService;
import com.custom555.adverto24.domain.advertisement.category.home_garden.tool.dto.ToolAdDto;
import com.custom555.adverto24.domain.advertisement.enums.State;
import com.custom555.adverto24.domain.advertisement.enums.ToolType;
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
@RequestMapping("/dom_ogrod/narzedzia")
public class ToolAdController {
    private final ToolAdService adService;
    private final UserService userService;

    @GetMapping
    public String getToolAdList(Model model){
        List<ToolAdDto> toolAds = adService.findAllToolAds();
        model.addAttribute("advertisements",toolAds);
        addConstAttributesToModel(model);
        return "listings/advertisement-listing";
    }
    @GetMapping("/search")
    public String searchForSelectedtoolAds(
            @RequestParam(value="state",required = false) State state,
            @RequestParam(value="type",required = false) ToolType type,
            @RequestParam(value="priceRangeBottom",required = false) Double priceRangeBottom,
            @RequestParam(value="priceRangeTop",required = false) Double priceRangeTop,
            Model model
    ){
        List<ToolAdDto> toolAds = adService.findToolAdsByParams(state,type,priceRangeBottom,priceRangeTop);
        model.addAttribute("advertisements",toolAds);
        addConstAttributesToModel(model);

        return "listings/advertisement-listing";
    }
    @GetMapping("/{id}")
    public String getToolAd(@PathVariable long id, Model model){
        Optional<ToolAdDto> optionalToolAd = adService.findToolAdById(id);
        if(optionalToolAd.isPresent()){
            ToolAdDto toolAd = optionalToolAd.get();
            Optional<UserRegistrationDto> optionalUser = userService.findUserById(toolAd.getOwnerId());
            optionalUser.ifPresent(user -> model.addAttribute("owner",user));
            model.addAttribute("advertisement",toolAd);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "ad-details";
    }
    private void addConstAttributesToModel(Model model){
        List<State> stateList = Arrays.stream(State.values()).toList();
        model.addAttribute("stateList",stateList);

        List<ToolType> typeList = Arrays.stream(ToolType.values()).toList();
        model.addAttribute("typeList",typeList);

        model.addAttribute("title","Narzędzia");
        model.addAttribute("fragment_name","home-search-bar");
        model.addAttribute("url","dom_ogrod/narzedzia");
    }
}
