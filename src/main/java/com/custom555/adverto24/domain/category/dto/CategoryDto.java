package com.custom555.adverto24.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDto {
    private String name;
    private String imgName;
    private String url;
    private List<SubcategoryDto> subcategories = new ArrayList<>();

}
