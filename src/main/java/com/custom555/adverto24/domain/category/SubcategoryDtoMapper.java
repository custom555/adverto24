package com.custom555.adverto24.domain.category;

import com.custom555.adverto24.domain.category.dto.SubcategoryDto;

public class SubcategoryDtoMapper {
    public static SubcategoryDto toDto(Subcategory subcategory) {
        return new SubcategoryDto(subcategory.getName(),subcategory.getUrl());
    }
}
