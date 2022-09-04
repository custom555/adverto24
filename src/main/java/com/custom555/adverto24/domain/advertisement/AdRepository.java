package com.custom555.adverto24.domain.advertisement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;


public interface AdRepository<T extends Ad> extends JpaRepository<T,Long> {
    List<T> findAll();
    Set<T> findByPriceBetween(Double priceRangeBottom, Double priceRangeTop);
    Set<T> findByPriceGreaterThanEqual(Double price);
    Set<T> findByPriceLessThanEqual(Double price);
    List<?> findAllByOwner_id(long id);
    List<?> findAllByPromotedIsTrue();
    List<?> findAllByOwner_email(String email);
}
