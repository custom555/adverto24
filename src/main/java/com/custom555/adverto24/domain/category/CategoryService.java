package com.custom555.adverto24.domain.category;

import com.custom555.adverto24.domain.category.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> findAllCategories(){
        return categoryRepository.findAll().stream()
                .map(CategoryDtoMapper::toDto)
                .toList();
    }
}
