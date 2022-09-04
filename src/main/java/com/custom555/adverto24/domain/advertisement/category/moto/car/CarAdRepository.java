package com.custom555.adverto24.domain.advertisement.category.moto.car;

import com.custom555.adverto24.domain.advertisement.AdRepository;
import com.custom555.adverto24.domain.advertisement.enums.CarBrand;
import com.custom555.adverto24.domain.advertisement.enums.State;

import java.util.Set;

public interface CarAdRepository extends AdRepository<CarAd> {
    Set<CarAd> findAllByState(State state);
    Set<CarAd> findAllByCarBrand(CarBrand brand);
    Set<CarAd> findAllByProdYear(Integer prodYear);
    Set<CarAd> findAllByMileageBetween(Integer mileageBottom,Integer mileageTop);
    Set<CarAd> findAllByMileageGreaterThanEqual(Integer mileageBottom);
    Set<CarAd> findAllByMileageLessThanEqual(Integer mileageTop);
    Set<CarAd> findAllByEnginePowerBetween(Integer enginePowerBottom,Integer enginePowerTop);
    Set<CarAd> findAllByEnginePowerGreaterThanEqual(Integer enginePowerBottom);
    Set<CarAd> findAllByEnginePowerLessThanEqual(Integer enginePowerTop);
}
