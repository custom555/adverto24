package com.custom555.adverto24.domain.advertisement.category.fashion.men;

import com.custom555.adverto24.domain.advertisement.Ad;
import com.custom555.adverto24.domain.advertisement.AdRepository;
import com.custom555.adverto24.domain.advertisement.category.electronics.telephone.TelephoneAd;
import com.custom555.adverto24.domain.advertisement.enums.FashionBrand;
import com.custom555.adverto24.domain.advertisement.enums.Size;
import com.custom555.adverto24.domain.advertisement.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Set;

public interface MensClothesAdRepository extends AdRepository<MensClothesAd> {
    Set<MensClothesAd> findAllByState(State state);
    Set<MensClothesAd> findAllByFashionBrand(FashionBrand brand);
    Set<MensClothesAd> findAllBySize(Size size);
}
