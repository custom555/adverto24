package com.custom555.adverto24.domain.advertisement.category.real_estate.flat;

import com.custom555.adverto24.domain.advertisement.category.real_estate.flat.dto.FlatAdDto;
import com.custom555.adverto24.domain.advertisement.category.real_estate.flat.dto.FlatAdSaveDto;
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
public class FlatAdService {
    private final FlatAdRepository adRepository;
    private final UserRepository userRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final FileStorageService fileStorageService;
    public List<FlatAdDto> findAllFlatAds() {
        List<FlatAd> allFlatAds = adRepository.findAll();
        return mapToDto(allFlatAds);
    }

    public List<FlatAdDto> findFlatAdsByParams(Integer roomCountBottom,
                                               Integer roomCountTop,
                                               Integer areaBottom,
                                               Integer areaTop,
                                               Double priceRangeBottom,
                                               Double priceRangeTop) {
        List<Set<FlatAd>> searchResults = new ArrayList<>();
        Set<FlatAd> allFlatAds = new HashSet<>(adRepository.findAll());
        searchResults.add(allFlatAds);

        Set<FlatAd> areaSearchAds = areaSearch(areaBottom, areaTop);
        addToSearchResults(areaSearchAds, searchResults);

        Set<FlatAd> roomCountSearchAds = roomCountSearch(roomCountBottom, roomCountTop);
        addToSearchResults(roomCountSearchAds, searchResults);

        Set<FlatAd> priceSearchAds = AdSearch.priceSearch(adRepository,priceRangeBottom,priceRangeTop);
        addToSearchResults(priceSearchAds, searchResults);

        Set<FlatAd> resultSet = SetIntersection.getIntersection(searchResults);
        return mapToDto(resultSet);
    }
    public Optional<FlatAdDto> findFlatAdById(long id) {
        return adRepository.findById(id).map(FlatAdDtoMapper::toDto);
    }
    private List<FlatAdDto> mapToDto(Collection<FlatAd> collection){
        return collection.stream()
                .map(FlatAdDtoMapper::toDto)
                .toList();
    }
    private Set<FlatAd> areaSearch(Integer areaBottom, Integer areaTop){
        if(areaBottom != null & areaTop != null){
            return adRepository.findAllByAreaBetween(areaBottom,areaTop);
        } else if (areaBottom != null & areaTop == null) {
            return adRepository.findAllByAreaGreaterThanEqual(areaBottom);
        } else if (areaTop != null) {
            return adRepository.findAllByAreaLessThanEqual(areaTop);
        }
        else return null;
    }
    private Set<FlatAd> roomCountSearch(Integer roomCountBottom, Integer roomCountTop){
        if(roomCountBottom != null & roomCountTop != null){
            return adRepository.findAllByRoomCountBetween(roomCountBottom,roomCountTop);
        } else if (roomCountBottom != null & roomCountTop == null) {
            return adRepository.findAllByRoomCountGreaterThanEqual(roomCountBottom);
        } else if (roomCountTop != null) {
            return adRepository.findAllByRoomCountLessThanEqual(roomCountTop);
        }
        else return null;
    }

    private void addToSearchResults(Set<FlatAd> searchAds, List<Set<FlatAd>> searchResults){
        if(searchAds != null){
            searchResults.add(searchAds);
        }
    }

    public void addFlatAd(FlatAdSaveDto adSaveDto, String subcategory) {
        FlatAd ad = new FlatAd();
        EntityMapper.mapSaveDtoToEntity(ad,
                adSaveDto,
                userRepository,
                subcategoryRepository,
                fileStorageService,
                subcategory);
        adRepository.save(ad);
    }


    public void deleteFlatAd(long id) {
        adRepository.deleteById(id);
    }
}
