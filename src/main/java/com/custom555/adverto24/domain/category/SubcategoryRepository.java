package com.custom555.adverto24.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubcategoryRepository extends JpaRepository<Subcategory,Long> {
    List<Subcategory> findAll();
    Optional<Subcategory> findByUrl(String subcategory);
}
