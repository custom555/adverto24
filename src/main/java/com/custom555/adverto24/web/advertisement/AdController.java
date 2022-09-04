package com.custom555.adverto24.web.advertisement;

import com.custom555.adverto24.domain.advertisement.AdDto;
import com.custom555.adverto24.domain.advertisement.AdService;
import com.custom555.adverto24.domain.user.UserService;
import com.custom555.adverto24.domain.user.dto.UserRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdController {
    private final AdService adService;
    private final UserService userService;

    @GetMapping("user/{id}")
    public String getAllAdsByUser(@PathVariable long id, Model model){
        Optional<UserRegistrationDto> optionalUser = userService.findUserById(id);
        if(optionalUser.isPresent()){
            List<AdDto> userAds = adService.findAllAdsByOwnerId(id);
            UserRegistrationDto owner = optionalUser.get();
            model.addAttribute("owner",owner);
            model.addAttribute("advertisements",userAds);
            model.addAttribute("title",
                    owner.getFirstName() +" "
                                +owner.getLastName() +" -ogłoszenia");
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        addConstAttributesToModel(model);
        return "listings/advertisement-listing";
    }
    private void addConstAttributesToModel(Model model){
        model.addAttribute("fragment_name","user-details-bar");
    }
}
