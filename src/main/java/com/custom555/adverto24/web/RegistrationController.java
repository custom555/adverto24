package com.custom555.adverto24.web;

import com.custom555.adverto24.domain.user.UserService;
import com.custom555.adverto24.domain.user.dto.UserRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping("/register")
    public String registrationForm(Model model){
        UserRegistrationDto userRegistration = new UserRegistrationDto();
        model.addAttribute("user",userRegistration);
        return "forms/registration-form";
    }
    @PostMapping("/register")
    public String register(UserRegistrationDto user){
        userService.register(user);
        return "redirect:/register/complete";
    }
    @GetMapping("/register/complete")
    public String confirmation(){
        return "registration-confirmation";
    }
}
