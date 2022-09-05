package com.custom555.adverto24.domain.advertisement.category.moto.car;

import com.custom555.adverto24.domain.advertisement.category.moto.car.dto.CarAdDto;
import com.custom555.adverto24.domain.advertisement.category.moto.car.dto.CarAdSaveDto;
import com.custom555.adverto24.domain.advertisement.enums.CarBrand;
import com.custom555.adverto24.domain.advertisement.enums.State;
import com.custom555.adverto24.domain.category.SubcategoryRepository;
import com.custom555.adverto24.domain.user.UserRepository;
import com.custom555.adverto24.storage.FileStorageService;
import com.custom555.adverto24.utils.AdSearch;
import com.custom555.adverto24.utils.EntityMapper;
import com.custom555.adverto24.utils.SetIntersection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarAdService {
    private final CarAdRepository adRepository;
    private final UserRepository userRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final FileStorageService fileStorageService;
    public List<CarAdDto> findAllCarAds() {
        List<CarAd> allCarAds = adRepository.findAll();
        return mapToDto(allCarAds);
    }

    public List<CarAdDto> findCarAdsByParams(State state,
                                             CarBrand brand,
                                             Integer prodYear,
                                             Double priceRangeBottom,
                                             Double priceRangeTop,
                                             Integer mileageBottom,
                                             Integer mileageTop,
                                             Integer enginePowerBottom,
                                             Integer enginePowerTop) {

        List<Set<CarAd>> searchResults = new ArrayList<>();
        Set<CarAd> allCarAds = new HashSet<>(adRepository.findAll());
        searchResults.add(allCarAds);

        Set<CarAd> stateSearchAds = stateSearch(state);
        addToSearchResults(stateSearchAds,searchResults);

        Set<CarAd> brandSearchAds = brandSearch(brand);
        addToSearchResults(brandSearchAds,searchResults);

        Set<CarAd> prodYearSearchAds = prodYearSearch(prodYear);
        addToSearchResults(prodYearSearchAds,searchResults);

        Set<CarAd> priceSearchAds = AdSearch.priceSearch(adRepository,priceRangeBottom,priceRangeTop);
        addToSearchResults(priceSearchAds,searchResults);

        Set<CarAd> mileageSearchAds = mileageSearch(mileageBottom,mileageTop);
        addToSearchResults(mileageSearchAds,searchResults);

        Set<CarAd> enginePowerSearchAds = enginePowerSearch(enginePowerBottom,enginePowerTop);
        addToSearchResults(enginePowerSearchAds,searchResults);

        Set<CarAd> resultSet = SetIntersection.getIntersection(searchResults);
        return mapToDto(resultSet);
    }
    public Optional<CarAdDto> findCarAdById(long id) {
        return adRepository.findById(id).map(CarAdDtoMapper::toDto);
    }
    private List<CarAdDto> mapToDto(Collection<CarAd> collection){
        return collection.stream()
                .map(CarAdDtoMapper::toDto)
                .collect(Collectors.toList());
    }
    private Set<CarAd> stateSearch(State state){
        if(state != null) {
            return adRepository.findAllByState(state);
        }
        else return null;
    }
    private Set<CarAd> prodYearSearch(Integer prodYear){
        if(prodYear != null) {
            return adRepository.findAllByProdYear(prodYear);
        }
        else return null;
    }
    private Set<CarAd> brandSearch(CarBrand brand){
        if(brand != null) {
            return adRepository.findAllByCarBrand(brand);
        }
        else return null;
    }
    private Set<CarAd> mileageSearch(Integer mileageBottom, Integer mileageTop){
        if(mileageBottom != null & mileageTop != null){
            return adRepository.findAllByMileageBetween(mileageBottom,mileageTop);
        } else if (mileageBottom != null & mileageTop == null) {
            return adRepository.findAllByMileageGreaterThanEqual(mileageBottom);
        } else if (mileageTop != null) {
            return adRepository.findAllByMileageLessThanEqual(mileageTop);
        }
        else return null;
    }
    private Set<CarAd> enginePowerSearch(Integer enginePowerBottom, Integer enginePowerTop){
        if(enginePowerBottom != null & enginePowerTop != null){
            return adRepository.findAllByEnginePowerBetween(enginePowerBottom,enginePowerTop);
        } else if (enginePowerBottom != null & enginePowerTop == null) {
            return adRepository.findAllByEnginePowerGreaterThanEqual(enginePowerBottom);
        } else if (enginePowerTop != null) {
            return adRepository.findAllByEnginePowerLessThanEqual(enginePowerTop);
        }
        else return null;
    }

    private void addToSearchResults(Set<CarAd> searchAds,List<Set<CarAd>> searchResults){
        if(searchAds != null){
            searchResults.add(searchAds);
        }
    }

    public void addCarAd(CarAdSaveDto adSaveDto, String subcategory) {
        CarAd ad = new CarAd();
        EntityMapper.mapSaveDtoToEntity(ad,
                adSaveDto,
                userRepository,
                subcategoryRepository,
                fileStorageService,
                subcategory);
        String stateVal = adSaveDto.getState();
        if(!stateVal.equals("empty")){
            ad.setState(State.valueOf(stateVal));
        }
        String brandVal = adSaveDto.getCarBrand();
        if(!brandVal.equals("empty")){
            ad.setCarBrand(CarBrand.valueOf(brandVal));
        }
        adRepository.save(ad);
    }

    public void deleteCarAd(long id) {
        adRepository.deleteById(id);
    }
}
