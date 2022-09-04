package com.custom555.adverto24.domain.advertisement;

import com.custom555.adverto24.domain.category.Subcategory;
import com.custom555.adverto24.domain.user.User;

public class AdDtoMapper {
    public static AdDto toDto(Ad ad, AdDto dto){
        dto.setId(ad.getId());
        dto.setName(ad.getName());
        dto.setBody(ad.getBody());
        dto.setImg(ad.getImg());
        dto.setPrice(ad.getPrice());

        User owner = ad.getOwner();
        dto.setOwnerId(owner.getId());

        Subcategory subcategory = ad.getSubcategory();
        dto.setSubcategoryUrl(subcategory.getUrl());
        dto.setCategoryUrl(subcategory.getCategory().getUrl());

        return dto;
    }

}
