package com.custom555.adverto24.domain.advertisement.category.fashion.women;

import com.custom555.adverto24.domain.advertisement.AdRepository;
import com.custom555.adverto24.domain.advertisement.enums.FashionBrand;
import com.custom555.adverto24.domain.advertisement.enums.Size;
import com.custom555.adverto24.domain.advertisement.enums.State;

import java.util.Set;

public interface WomenClothesAdRepository extends AdRepository<WomenClothesAd> {
    Set<WomenClothesAd> findAllByState(State state);
    Set<WomenClothesAd> findAllByFashionBrand(FashionBrand brand);
    Set<WomenClothesAd> findAllBySize(Size size);
}
