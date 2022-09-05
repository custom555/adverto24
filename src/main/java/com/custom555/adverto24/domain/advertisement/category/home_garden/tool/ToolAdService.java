package com.custom555.adverto24.domain.advertisement.category.home_garden.tool;

import com.custom555.adverto24.domain.advertisement.category.home_garden.tool.dto.ToolAdDto;
import com.custom555.adverto24.domain.advertisement.category.home_garden.tool.dto.ToolAdSaveDto;
import com.custom555.adverto24.domain.advertisement.enums.State;
import com.custom555.adverto24.domain.advertisement.enums.ToolType;
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
public class ToolAdService {
    private final ToolAdRepository adRepository;
    private final UserRepository userRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final FileStorageService fileStorageService;

    public List<ToolAdDto> findAllToolAds() {
        List<ToolAd> allToolAds = adRepository.findAll();
        return mapToDto(allToolAds);
    }

    public List<ToolAdDto> findToolAdsByParams(State state,
                                               ToolType type,
                                               Double priceRangeBottom,
                                               Double priceRangeTop) {

        List<Set<ToolAd>> searchResults = new ArrayList<>();
        Set<ToolAd> allFurnitureAds = new HashSet<>(adRepository.findAll());
        searchResults.add(allFurnitureAds);

        Set<ToolAd> stateSearchAds = stateSearch(state);
        addToSearchResults(stateSearchAds, searchResults);

        Set<ToolAd> typeSearchAds = typeSearch(type);
        addToSearchResults(typeSearchAds, searchResults);

        Set<ToolAd> priceSearchAds = AdSearch.priceSearch(adRepository,priceRangeBottom,priceRangeTop);
        addToSearchResults(priceSearchAds, searchResults);

        Set<ToolAd> resultSet = SetIntersection.getIntersection(searchResults);
        return mapToDto(resultSet);
    }

    public Optional<ToolAdDto> findToolAdById(long id) {
        return adRepository.findById(id).map(ToolAdDtoMapper::toDto);
    }
    private List<ToolAdDto> mapToDto(Collection<ToolAd> collection){
        return collection.stream()
                .map(ToolAdDtoMapper::toDto)
                .collect(Collectors.toList());
    }
    private Set<ToolAd> stateSearch(State state){
        if(state != null) {
            return adRepository.findAllByState(state);
        }
        else return null;
    }
    private Set<ToolAd> typeSearch(ToolType type){
        if(type != null) {
            return adRepository.findAllByToolType(type);
        }
        else return null;
    }

    private void addToSearchResults(Set<ToolAd> searchAds, List<Set<ToolAd>> searchResults){
        if(searchAds != null){
            searchResults.add(searchAds);
        }
    }

    public void addToolAd(ToolAdSaveDto adSaveDto, String subcategory) {
        ToolAd ad = new ToolAd();
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
        String typeVal = adSaveDto.getToolType();
        if(!typeVal.equals("empty")){
            ad.setToolType(ToolType.valueOf(typeVal));
        }
        adRepository.save(ad);
    }
    public void deleteToolAd(long id) {
        adRepository.deleteById(id);
    }
}
