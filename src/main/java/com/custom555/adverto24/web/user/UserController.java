package com.custom555.adverto24.web.user;

import com.custom555.adverto24.domain.advertisement.AdDto;
import com.custom555.adverto24.domain.advertisement.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final AdService adService;

    @GetMapping("/my-account")
    public String getUserAccount(Authentication authentication, Model model){
        List<AdDto> userAds = adService.findAllAdsByOwnerEmail(authentication.getName());

        model.addAttribute("advertisements",userAds);
        model.addAttribute("fragment_name","user-account-bar");
        model.addAttribute("title","Twoje konto");

        return "listings/user-account-listing";
    }
}
