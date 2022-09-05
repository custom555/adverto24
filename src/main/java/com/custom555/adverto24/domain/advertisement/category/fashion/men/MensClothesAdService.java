package com.custom555.adverto24.domain.advertisement.category.fashion.men;

import com.custom555.adverto24.domain.advertisement.category.fashion.men.dto.MensClothesAdDto;
import com.custom555.adverto24.domain.advertisement.category.fashion.men.dto.MensClothesAdSaveDto;
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
public class MensClothesAdService {
    private final UserRepository userRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final MensClothesAdRepository adRepository;
    private final FileStorageService fileStorageService;
    public List<MensClothesAdDto> findAllMensClothesAds() {
        List<MensClothesAd> allMensClothesAds = adRepository.findAll();
        return mapToDto(allMensClothesAds);
    }

    public List<MensClothesAdDto> findMensClothesAdsByParams(State state,
                                                             Size size,
                                                             FashionBrand brand,
                                                             Double priceRangeBottom,
                                                             Double priceRangeTop) {

        List<Set<MensClothesAd>> searchResults = new ArrayList<>();
        Set<MensClothesAd> allMensClothesAds = new HashSet<>(adRepository.findAll());
        searchResults.add(allMensClothesAds);

        Set<MensClothesAd> stateSearchAds = stateSearch(state);
        addToSearchResults(stateSearchAds, searchResults);

        Set<MensClothesAd> sizeSearchAds = sizeSearch(size);
        addToSearchResults(sizeSearchAds, searchResults);

        Set<MensClothesAd> brandSearchAds = brandSearch(brand);
        addToSearchResults(brandSearchAds, searchResults);

        Set<MensClothesAd> priceSearchAds = AdSearch.priceSearch(adRepository,priceRangeBottom,priceRangeTop);
        addToSearchResults(priceSearchAds, searchResults);

        Set<MensClothesAd> resultSet = SetIntersection.getIntersection(searchResults);
        return mapToDto(resultSet);
    }
    public Optional<MensClothesAdDto> findMensClothesAdById(long id) {
        return adRepository.findById(id).map(MensClothesAdDtoMapper::toDto);
    }
    private List<MensClothesAdDto> mapToDto(Collection<MensClothesAd> collection){
        return collection.stream()
                .map(MensClothesAdDtoMapper::toDto)
                .collect(Collectors.toList());
    }
    private Set<MensClothesAd> stateSearch(State state){
        if(state != null) {
            return adRepository.findAllByState(state);
        }
        else return null;
    }
    private Set<MensClothesAd> sizeSearch(Size size){
        if(size != null) {
            return adRepository.findAllBySize(size);
        }
        else return null;
    }
    private Set<MensClothesAd> brandSearch(FashionBrand brand){
        if(brand != null) {
            return adRepository.findAllByFashionBrand(brand);
        }
        else return null;
    }
    private void addToSearchResults(Set<MensClothesAd> searchAds, List<Set<MensClothesAd>> searchResults){
        if(searchAds != null){
            searchResults.add(searchAds);
        }
    }
    public void addMensClothesAd(MensClothesAdSaveDto adSaveDto, String subcategory){
        MensClothesAd ad = new MensClothesAd();

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
    public void deleteMensClothesAd(long id) {
        adRepository.deleteById(id);
    }
}
