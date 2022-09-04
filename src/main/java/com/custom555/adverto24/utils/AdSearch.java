package com.custom555.adverto24.utils;

import com.custom555.adverto24.domain.advertisement.Ad;
import com.custom555.adverto24.domain.advertisement.AdRepository;

import java.util.Set;

public class AdSearch {
    public static <T extends Ad> Set<T> priceSearch(AdRepository<T> repository, Double priceRangeBottom, Double priceRangeTop){
        if(priceRangeBottom != null & priceRangeTop != null){
            return repository.findByPriceBetween(priceRangeBottom,priceRangeTop);
        } else if (priceRangeBottom != null & priceRangeTop == null) {
            return repository.findByPriceGreaterThanEqual(priceRangeBottom);
        } else if (priceRangeTop != null) {
            return repository.findByPriceLessThanEqual(priceRangeTop);
        }
        else return null;
    }
}
