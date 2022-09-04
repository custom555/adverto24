package com.custom555.adverto24.domain.advertisement.category.moto.motorbike;

import com.custom555.adverto24.domain.advertisement.category.moto.motorbike.dto.MotorbikeAdDto;
import com.custom555.adverto24.domain.advertisement.category.moto.motorbike.dto.MotorbikeAdSaveDto;
import com.custom555.adverto24.domain.advertisement.enums.MotorbikeBrand;
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

@Service
@RequiredArgsConstructor
public class MotorbikeAdService {
    private final MotorbikeAdRepository adRepository;
    private final UserRepository userRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final FileStorageService fileStorageService;

    public List<MotorbikeAdDto> findAllMotorbikeAds() {
        List<MotorbikeAd> allMotorbikeAds = adRepository.findAll();
        return mapToDto(allMotorbikeAds);
    }

    public List<MotorbikeAdDto> findMotorbikeAdsByParams(State state,
                                                         MotorbikeBrand brand,
                                                         Integer prodYear,
                                                         Double priceRangeBottom,
                                                         Double priceRangeTop,
                                                         Integer mileageBottom,
                                                         Integer mileageTop,
                                                         Integer enginePowerBottom,
                                                         Integer enginePowerTop) {
        List<Set<MotorbikeAd>> searchResults = new ArrayList<>();
        Set<MotorbikeAd> allCarAds = new HashSet<>(adRepository.findAll());
        searchResults.add(allCarAds);

        Set<MotorbikeAd> stateSearchAds = stateSearch(state);
        addToSearchResults(stateSearchAds,searchResults);

        Set<MotorbikeAd> brandSearchAds = brandSearch(brand);
        addToSearchResults(brandSearchAds,searchResults);

        Set<MotorbikeAd> prodYearSearchAds = prodYearSearch(prodYear);
        addToSearchResults(prodYearSearchAds,searchResults);

        Set<MotorbikeAd> priceSearchAds = AdSearch.priceSearch(adRepository,priceRangeBottom,priceRangeTop);
        addToSearchResults(priceSearchAds,searchResults);

        Set<MotorbikeAd> mileageSearchAds = mileageSearch(mileageBottom,mileageTop);
        addToSearchResults(mileageSearchAds,searchResults);

        Set<MotorbikeAd> enginePowerSearchAds = enginePowerSearch(enginePowerBottom,enginePowerTop);
        addToSearchResults(enginePowerSearchAds,searchResults);

        Set<MotorbikeAd> resultSet = SetIntersection.getIntersection(searchResults);
        return mapToDto(resultSet);
    }
    public Optional<MotorbikeAdDto> findMotorbikeAdById(long id) {
        return adRepository.findById(id).map(MotorbikeAdDtoMapper::toDto);
    }
    private List<MotorbikeAdDto> mapToDto(Collection<MotorbikeAd> collection){
        return collection.stream()
                .map(MotorbikeAdDtoMapper::toDto)
                .toList();
    }
    private Set<MotorbikeAd> stateSearch(State state){
        if(state != null) {
            return adRepository.findAllByState(state);
        }
        else return null;
    }
    private Set<MotorbikeAd> prodYearSearch(Integer prodYear){
        if(prodYear != null) {
            return adRepository.findAllByProdYear(prodYear);
        }
        else return null;
    }
    private Set<MotorbikeAd> brandSearch(MotorbikeBrand brand){
        if(brand != null) {
            return adRepository.findAllByMotorbikeBrand(brand);
        }
        else return null;
    }
    private Set<MotorbikeAd> mileageSearch(Integer mileageBottom, Integer mileageTop){
        if(mileageBottom != null & mileageTop != null){
            return adRepository.findAllByMileageBetween(mileageBottom,mileageTop);
        } else if (mileageBottom != null & mileageTop == null) {
            return adRepository.findAllByMileageGreaterThanEqual(mileageBottom);
        } else if (mileageTop != null) {
            return adRepository.findAllByMileageLessThanEqual(mileageTop);
        }
        else return null;
    }

    private Set<MotorbikeAd> enginePowerSearch(Integer enginePowerBottom, Integer enginePowerTop){
        if(enginePowerBottom != null & enginePowerTop != null){
            return adRepository.findAllByEnginePowerBetween(enginePowerBottom,enginePowerTop);
        } else if (enginePowerBottom != null & enginePowerTop == null) {
            return adRepository.findAllByEnginePowerGreaterThanEqual(enginePowerBottom);
        } else if (enginePowerTop != null) {
            return adRepository.findAllByEnginePowerLessThanEqual(enginePowerTop);
        }
        else return null;
    }

    private void addToSearchResults(Set<MotorbikeAd> searchAds,List<Set<MotorbikeAd>> searchResults){
        if(searchAds != null){
            searchResults.add(searchAds);
        }
    }

    public void addMotorbikeAd(MotorbikeAdSaveDto adSaveDto, String subcategory) {
        MotorbikeAd ad = new MotorbikeAd();
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
        String brandVal = adSaveDto.getMotorbikeBrand();
        if(!brandVal.equals("empty")){
            ad.setMotorbikeBrand(MotorbikeBrand.valueOf(brandVal));
        }
        adRepository.save(ad);
    }

    public void deleteMotorbikeAd(long id) {
        adRepository.deleteById(id);
    }
}
