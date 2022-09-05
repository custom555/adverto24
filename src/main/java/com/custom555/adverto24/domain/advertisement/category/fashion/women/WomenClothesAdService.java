package com.custom555.adverto24.domain.advertisement.category.fashion.women;

import com.custom555.adverto24.domain.advertisement.category.fashion.women.dto.WomenClothesAdDto;
import com.custom555.adverto24.domain.advertisement.category.fashion.women.dto.WomenClothesAdSaveDto;
import com.custom555.adverto24.domain.advertisement.enums.FashionBrand;
import com.custom555.adverto24.domain.advertisement.enums.Size;
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
public class WomenClothesAdService {
    private final WomenClothesAdRepository adRepository;
    private final UserRepository userRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final FileStorageService fileStorageService;

    public List<WomenClothesAdDto> findAllWomenClothesAds() {
        List<WomenClothesAd> allWomenClothesAds = adRepository.findAll();
        return mapToDto(allWomenClothesAds);
    }
    public Optional<WomenClothesAdDto> findWomenClothesAdById(long id) {
        return adRepository.findById(id).map(WomenClothesAdDtoMapper::toDto);
    }
    public List<WomenClothesAdDto> findWomenClothesAdsByParams(State state,
                                                               Size size,
                                                               FashionBrand brand,
                                                               Double priceRangeBottom,
                                                               Double priceRangeTop) {

        List<Set<WomenClothesAd>> searchResults = new ArrayList<>();
        Set<WomenClothesAd> allWomenClothesAds = new HashSet<>(adRepository.findAll());
        searchResults.add(allWomenClothesAds);

        Set<WomenClothesAd> stateSearchAds = stateSearch(state);
        addToSearchResults(stateSearchAds, searchResults);

        Set<WomenClothesAd> sizeSearchAds = sizeSearch(size);
        addToSearchResults(sizeSearchAds, searchResults);

        Set<WomenClothesAd> brandSearchAds = brandSearch(brand);
        addToSearchResults(brandSearchAds, searchResults);

        Set<WomenClothesAd> priceSearchAds = AdSearch.priceSearch(adRepository,priceRangeBottom,priceRangeTop);
        addToSearchResults(priceSearchAds, searchResults);

        Set<WomenClothesAd> resultSet = SetIntersection.getIntersection(searchResults);
        return mapToDto(resultSet);
    }

    private List<WomenClothesAdDto> mapToDto(Collection<WomenClothesAd> collection){
        return collection.stream()
                .map(WomenClothesAdDtoMapper::toDto)
                .collect(Collectors.toList());
    }
    private Set<WomenClothesAd> stateSearch(State state){
        if(state != null) {
            return adRepository.findAllByState(state);
        }
        else return null;
    }
    private Set<WomenClothesAd> sizeSearch(Size size){
        if(size != null) {
            return adRepository.findAllBySize(size);
        }
        else return null;
    }
    private Set<WomenClothesAd> brandSearch(FashionBrand brand){
        if(brand != null) {
            return adRepository.findAllByFashionBrand(brand);
        }
        else return null;
    }
    private void addToSearchResults(Set<WomenClothesAd> searchAds, List<Set<WomenClothesAd>> searchResults){
        if(searchAds != null){
            searchResults.add(searchAds);
        }
    }
    public void addWomenClothesAd(WomenClothesAdSaveDto adSaveDto, String subcategory) {
        WomenClothesAd ad = new WomenClothesAd();

        EntityMapper.mapSaveDtoToEntity(ad,
                                        adSaveDto,
                                        userRepository,
                                        subcategoryRepository,
                                        fileStorageService,
                                        subcategory);
        String sizeVal = adSaveDto.getSize();
        if(!sizeVal.equals("empty")){
            ad.setSize(Size.valueOf(sizeVal));
        }
        String stateVal = adSaveDto.getState();
        if(!stateVal.equals("empty")){
            ad.setState(State.valueOf(stateVal));
        }
        String brandVal = adSaveDto.getFashionBrand();
        if(!brandVal.equals("empty")) {
            ad.setFashionBrand(FashionBrand.valueOf(brandVal));
        }
        adRepository.save(ad);
    }

    public void deleteWomenClothesAd(long id) {
        adRepository.deleteById(id);
    }
}
