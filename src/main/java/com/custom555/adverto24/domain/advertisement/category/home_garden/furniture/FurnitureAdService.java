package com.custom555.adverto24.domain.advertisement.category.home_garden.furniture;

import com.custom555.adverto24.domain.advertisement.category.home_garden.furniture.dto.FurnitureAdDto;
import com.custom555.adverto24.domain.advertisement.category.home_garden.furniture.dto.FurnitureAdSaveDto;
import com.custom555.adverto24.domain.advertisement.enums.FurnitureType;
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
public class FurnitureAdService {
    private final FurnitureAdRepository adRepository;
    private final UserRepository userRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final FileStorageService fileStorageService;
    public List<FurnitureAdDto> findAllFurnitureAds() {
        List<FurnitureAd> allFurnitureAds = adRepository.findAll();
        return mapToDto(allFurnitureAds);
    }
    public List<FurnitureAdDto> findFurnitureAdsByParams(State state,
                                                         FurnitureType type,
                                                         Double priceRangeBottom,
                                                         Double priceRangeTop) {

        List<Set<FurnitureAd>> searchResults = new ArrayList<>();
        Set<FurnitureAd> allFurnitureAds = new HashSet<>(adRepository.findAll());
        searchResults.add(allFurnitureAds);

        Set<FurnitureAd> stateSearchAds = stateSearch(state);
        addToSearchResults(stateSearchAds, searchResults);

        Set<FurnitureAd> typeSearchAds = typeSearch(type);
        addToSearchResults(typeSearchAds, searchResults);

        Set<FurnitureAd> priceSearchAds = AdSearch.priceSearch(adRepository,priceRangeBottom,priceRangeTop);
        addToSearchResults(priceSearchAds, searchResults);

        Set<FurnitureAd> resultSet = SetIntersection.getIntersection(searchResults);
        return mapToDto(resultSet);
    }
    public Optional<FurnitureAdDto> findFurnitureAdById(long id) {
        return adRepository.findById(id).map(FurnitureAdDtoMapper::toDto);
    }
    private List<FurnitureAdDto> mapToDto(Collection<FurnitureAd> collection){
        return collection.stream()
                .map(FurnitureAdDtoMapper::toDto)
                .toList();
    }
    private Set<FurnitureAd> stateSearch(State state){
        if(state != null) {
            return adRepository.findAllByState(state);
        }
        else return null;
    }
    private Set<FurnitureAd> typeSearch(FurnitureType type){
        if(type != null) {
            return adRepository.findAllByFurnitureType(type);
        }
        else return null;
    }
    private void addToSearchResults(Set<FurnitureAd> searchAds, List<Set<FurnitureAd>> searchResults){
        if(searchAds != null){
            searchResults.add(searchAds);
        }
    }

    public void addFurnitureAd(FurnitureAdSaveDto adSaveDto, String subcategory) {
        FurnitureAd ad = new FurnitureAd();
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
        String typeVal = adSaveDto.getFurnitureType();
        if(!typeVal.equals("empty")){
            ad.setFurnitureType(FurnitureType.valueOf(typeVal));
        }
        adRepository.save(ad);
    }

    public void deleteFurnitureAd(long id) {
        adRepository.deleteById(id);
    }
}
