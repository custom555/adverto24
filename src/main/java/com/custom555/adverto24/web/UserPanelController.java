package com.custom555.adverto24.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserPanelController {

    @GetMapping("/my-panel")
    public String panel(Authentication authentication){
        if(authentication.getAuthorities().stream().anyMatch(authority->authority.getAuthority().equals("ROLE_ADMIN")))
            return "admin-panel";
        else
            return "user-panel";
    }
}
