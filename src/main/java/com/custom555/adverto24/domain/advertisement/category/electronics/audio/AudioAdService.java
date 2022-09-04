package com.custom555.adverto24.domain.advertisement.category.electronics.audio;

import com.custom555.adverto24.domain.advertisement.category.electronics.audio.dto.AudioAdDto;
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

@Service
@RequiredArgsConstructor
public class AudioAdService {
    private final AudioAdRepository adRepository;
    private final UserRepository userRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final FileStorageService fileStorageService;

    public List<AudioAdDto> findAllAudioAds() {
        List<AudioAd> allAudioAds = adRepository.findAll();
        return mapToDto(allAudioAds);

    }
    public Optional<AudioAdDto> findAudioAdById(long id) {
        return adRepository.findById(id).map(AudioAdDtoMapper::toDto);
    }
    public List<AudioAdDto> findAudioAdsByParams(State state, Double priceRangeBottom, Double priceRangeTop) {
        List<Set<AudioAd>> searchResults = new ArrayList<>();
        Set<AudioAd> allAudioAds = new HashSet<>(adRepository.findAll());
        searchResults.add(allAudioAds);

        Set<AudioAd> stateSearchAds = stateSearch(state);
        addToSearchResults(stateSearchAds, searchResults);

        Set<AudioAd> priceSearchAds = AdSearch.priceSearch(adRepository,priceRangeBottom,priceRangeTop);
        addToSearchResults(priceSearchAds, searchResults);

        Set<AudioAd> resultSet = SetIntersection.getIntersection(searchResults);
        return mapToDto(resultSet);
    }
    private List<AudioAdDto> mapToDto(Collection<AudioAd> collection){
        return collection.stream()
                .map(AudioAdDtoMapper::toDto)
                .toList();
    }
    private Set<AudioAd> stateSearch(State state){
        if(state != null) {
            return adRepository.findAllByState(state);
        }
        else return null;
    }
    private void addToSearchResults(Set<AudioAd> searchAds, List<Set<AudioAd>> searchResults){
        if(searchAds != null){
            searchResults.add(searchAds);
        }
    }

    public void addAudioAd(TelephoneAdSaveDto adSaveDto, String subcategory) {
        AudioAd ad = new AudioAd();
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
    public void deleteAudioAd(long id) {
        adRepository.deleteById(id);
    }
}

