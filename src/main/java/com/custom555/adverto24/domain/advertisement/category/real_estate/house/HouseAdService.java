package com.custom555.adverto24.domain.advertisement.category.real_estate.house;

import com.custom555.adverto24.domain.advertisement.category.real_estate.house.dto.HouseAdDto;
import com.custom555.adverto24.domain.advertisement.category.real_estate.house.dto.HouseAdSaveDto;
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
public class HouseAdService {
    private final HouseAdRepository adRepository;
    private final UserRepository userRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final FileStorageService fileStorageService;
    public List<HouseAdDto> findAllHouseAds() {
        List<HouseAd> allHouseAds = adRepository.findAll();
        return mapToDto(allHouseAds);
    }

    public List<HouseAdDto> findHouseAdsByParams(Integer floorCountBottom,
                                                 Integer floorCountTop,
                                                 Integer areaBottom,
                                                 Integer areaTop,
                                                 Double priceRangeBottom,
                                                 Double priceRangeTop) {
        List<Set<HouseAd>> searchResults = new ArrayList<>();
        Set<HouseAd> allHouseAds = new HashSet<>(adRepository.findAll());
        searchResults.add(allHouseAds);

        Set<HouseAd> areaSearchAds = areaSearch(areaBottom, areaTop);
        addToSearchResults(areaSearchAds, searchResults);

        Set<HouseAd> floorCountSearchAds = floorCountSearch(floorCountBottom, floorCountTop);
        addToSearchResults(floorCountSearchAds, searchResults);

        Set<HouseAd> priceSearchAds = AdSearch.priceSearch(adRepository,priceRangeBottom,priceRangeTop);
        addToSearchResults(priceSearchAds, searchResults);

        Set<HouseAd> resultSet = SetIntersection.getIntersection(searchResults);
        return mapToDto(resultSet);
    }
    public Optional<HouseAdDto> findHouseAdById(long id) {
        return adRepository.findById(id).map(HouseAdDtoMapper::toDto);
    }
    private List<HouseAdDto> mapToDto(Collection<HouseAd> collection){
        return collection.stream()
                .map(HouseAdDtoMapper::toDto)
                .collect(Collectors.toList());
    }
    private Set<HouseAd> areaSearch(Integer areaBottom, Integer areaTop){
        if(areaBottom != null & areaTop != null){
            return adRepository.findAllByAreaBetween(areaBottom,areaTop);
        } else if (areaBottom != null & areaTop == null) {
            return adRepository.findAllByAreaGreaterThanEqual(areaBottom);
        } else if (areaTop != null) {
            return adRepository.findAllByAreaLessThanEqual(areaTop);
        }
        else return null;
    }
    private Set<HouseAd> floorCountSearch(Integer floorCountBottom, Integer floorCountTop){
        if(floorCountBottom != null & floorCountTop != null){
            return adRepository.findAllByFloorCountBetween(floorCountBottom,floorCountTop);
        } else if (floorCountBottom != null & floorCountTop == null) {
            return adRepository.findAllByFloorCountGreaterThanEqual(floorCountBottom);
        } else if (floorCountTop != null) {
            return adRepository.findAllByFloorCountLessThanEqual(floorCountTop);
        }
        else return null;
    }
    private void addToSearchResults(Set<HouseAd> searchAds, List<Set<HouseAd>> searchResults){
        if(searchAds != null){
            searchResults.add(searchAds);
        }
    }

    public void addHouseAd(HouseAdSaveDto adSaveDto, String subcategory) {
        HouseAd ad = new HouseAd();
        EntityMapper.mapSaveDtoToEntity(ad,
                adSaveDto,
                userRepository,
                subcategoryRepository,
                fileStorageService,
                subcategory);
        adRepository.save(ad);
    }

    public void deleteHouseAd(long id) {
        adRepository.deleteById(id);
    }
}
