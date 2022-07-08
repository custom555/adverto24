package com.custom555.adverto24.domain.category;

import com.custom555.adverto24.domain.category.dto.CategoryDto;

public class CategoryDtoMapper {
    public static CategoryDto toDto(Category category){
        return new CategoryDto(category.getId(), category.getName(),category.getImgUrl());
    }
}
