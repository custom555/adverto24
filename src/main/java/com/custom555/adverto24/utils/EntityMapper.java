package com.custom555.adverto24.utils;

import com.custom555.adverto24.domain.advertisement.Ad;
import com.custom555.adverto24.domain.advertisement.AdSaveDto;
import com.custom555.adverto24.domain.category.Subcategory;
import com.custom555.adverto24.domain.category.SubcategoryRepository;
import com.custom555.adverto24.domain.user.User;
import com.custom555.adverto24.domain.user.UserRepository;
import com.custom555.adverto24.storage.FileStorageService;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.Optional;

public class EntityMapper {
    public static <A extends Ad,D extends AdSaveDto> void mapSaveDtoToEntity(A ad,
                                                                             D adSaveDto,
                                                                             UserRepository userRepository,
                                                                             SubcategoryRepository subcategoryRepository,
                                                                             FileStorageService fileStorageService,
                                                                             String subcategory){
        ad.setName(adSaveDto.getName());
        ad.setBody(adSaveDto.getBody());
        ad.setPrice(adSaveDto.getPrice());
        ad.setCreated(LocalDate.now());
        ad.setPromoted(false);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> optionalOwner = userRepository.findByEmail(username);
        optionalOwner.ifPresent(ad::setOwner);

        Optional<Subcategory> optionalSubcategory = subcategoryRepository.findByUrl(subcategory);
        optionalSubcategory.ifPresent(ad::setSubcategory);

        if(!adSaveDto.getImg().isEmpty()){
            String savedFileName = fileStorageService.saveImage(adSaveDto.getImg());
            ad.setImg(savedFileName);
        }
    }
}
