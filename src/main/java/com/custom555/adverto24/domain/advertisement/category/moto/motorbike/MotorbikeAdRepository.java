package com.custom555.adverto24.domain.advertisement.category.moto.motorbike;

import com.custom555.adverto24.domain.advertisement.AdRepository;
import com.custom555.adverto24.domain.advertisement.enums.CarBrand;
import com.custom555.adverto24.domain.advertisement.enums.MotorbikeBrand;
import com.custom555.adverto24.domain.advertisement.enums.State;

import java.util.Set;

public interface MotorbikeAdRepository extends AdRepository<MotorbikeAd> {

    Set<MotorbikeAd> findAllByState(State state);
    Set<MotorbikeAd> findAllByMotorbikeBrand(MotorbikeBrand brand);
    Set<MotorbikeAd> findAllByProdYear(Integer prodYear);
    Set<MotorbikeAd> findAllByMileageBetween(Integer mileageBottom,Integer mileageTop);
    Set<MotorbikeAd> findAllByMileageGreaterThanEqual(Integer mileageBottom);
    Set<MotorbikeAd> findAllByMileageLessThanEqual(Integer mileageTop);
    Set<MotorbikeAd> findAllByEnginePowerBetween(Integer enginePowerBottom,Integer enginePowerTop);
    Set<MotorbikeAd> findAllByEnginePowerGreaterThanEqual(Integer enginePowerBottom);
    Set<MotorbikeAd> findAllByEnginePowerLessThanEqual(Integer enginePowerTop);
}
