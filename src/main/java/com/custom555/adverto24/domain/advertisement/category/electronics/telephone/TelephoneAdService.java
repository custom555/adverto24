package com.custom555.adverto24.domain.advertisement.category.electronics.telephone;

import com.custom555.adverto24.domain.advertisement.category.electronics.telephone.dto.TelephoneAdDto;
import com.custom555.adverto24.domain.advertisement.category.electronics.telephone.dto.TelephoneAdSaveDto;
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
public class TelephoneAdService {
    private final TelephoneAdRepository adRepository;
    private final UserRepository userRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final FileStorageService fileStorageService;
    public List<TelephoneAdDto> findAllTelephoneAds() {
        List<TelephoneAd> allTelephoneAds= adRepository.findAll();
        return mapToDto(allTelephoneAds);
    }
    public Optional<TelephoneAdDto> findTelephoneAdById(long id) {
        return adRepository.findById(id).map(TelephoneAdDtoMapper::toDto);
    }
    public List<TelephoneAdDto> findTelephoneAdsByParams(State state, Double priceRangeBottom, Double priceRangeTop) {
        List<Set<TelephoneAd>> searchResults = new ArrayList<>();
        Set<TelephoneAd> allTelephoneAds = new HashSet<>(adRepository.findAll());
        searchResults.add(allTelephoneAds);

        Set<TelephoneAd> stateSearchAds = stateSearch(state);
        addToSearchResults(stateSearchAds, searchResults);

        Set<TelephoneAd> priceSearchAds = AdSearch.priceSearch(adRepository,priceRangeBottom,priceRangeTop);
        addToSearchResults(priceSearchAds, searchResults);

        Set<TelephoneAd> resultSet = SetIntersection.getIntersection(searchResults);
        return mapToDto(resultSet);
    }
    private List<TelephoneAdDto> mapToDto(Collection<TelephoneAd> collection){
        return collection.stream()
                .map(TelephoneAdDtoMapper::toDto)
                .collect(Collectors.toList());
    }
    private Set<TelephoneAd> stateSearch(State state){
        if(state != null) {
            return adRepository.findAllByState(state);
        }
        else return null;
    }
    private void addToSearchResults(Set<TelephoneAd> searchAds, List<Set<TelephoneAd>> searchResults){
        if(searchAds != null){
            searchResults.add(searchAds);
        }
    }
    public void addTelephoneAd(TelephoneAdSaveDto adSaveDto, String subcategory) {
        TelephoneAd ad = new TelephoneAd();
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
        adRepository.save(ad);
    }
    public void deleteTelephoneAd(long id) {
        adRepository.deleteById(id);
    }
}
