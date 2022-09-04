package com.custom555.adverto24.domain.category;

import com.custom555.adverto24.domain.category.dto.CategoryDto;
import com.custom555.adverto24.domain.category.dto.SubcategoryDto;

import java.util.List;

public class CategoryDtoMapper {
    public static CategoryDto toDto(Category category){
        List<SubcategoryDto> subcategories = category.getSubcategories()
                .stream()
                .map(SubcategoryDtoMapper::toDto)
                .toList();

        return new CategoryDto(
                category.getName(),
                category.getImgName(),
                category.getUrl(),
                subcategories);
    }
}
