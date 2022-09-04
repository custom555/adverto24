package com.custom555.adverto24.domain.advertisement.category.work.IT;

import com.custom555.adverto24.domain.advertisement.category.work.IT.dto.ItWorkAdDto;
import com.custom555.adverto24.domain.advertisement.category.work.IT.dto.ItWorkAdSaveDto;
import com.custom555.adverto24.domain.advertisement.enums.WorkingTime;
import com.custom555.adverto24.domain.category.SubcategoryRepository;
import com.custom555.adverto24.domain.user.UserRepository;
import com.custom555.adverto24.storage.FileStorageService;
import com.custom555.adverto24.utils.EntityMapper;
import com.custom555.adverto24.utils.SetIntersection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ItWorkAdService {
    private final ItWorkAdRepository adRepository;
    private final UserRepository userRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final FileStorageService fileStorageService;

    public List<ItWorkAdDto> findAllItWorkAds() {
        List<ItWorkAd> allItWorkAds = adRepository.findAll();
        return mapToDto(allItWorkAds);
    }

    public List<ItWorkAdDto> findItWorkAdsByParams(WorkingTime workingTime, Boolean workFromHome) {
        List<Set<ItWorkAd>> searchResults = new ArrayList<>();
        Set<ItWorkAd> allItWorkAds = new HashSet<>(adRepository.findAll());
        searchResults.add(allItWorkAds);

        Set<ItWorkAd> workingTimeSearchAds = workingTimeSearch(workingTime);
        addToSearchResults(workingTimeSearchAds, searchResults);

        Set<ItWorkAd> workFromHomeAds = workFromHomeSearch(workFromHome);
        addToSearchResults(workFromHomeAds, searchResults);

        Set<ItWorkAd> resultSet = SetIntersection.getIntersection(searchResults);
        return mapToDto(resultSet);
    }
    public Optional<ItWorkAdDto> findItWorkAdById(long id) {
        return adRepository.findById(id).map(ItWorkAdDtoMapper::toDto);
    }
    private List<ItWorkAdDto> mapToDto(Collection<ItWorkAd> collection){
        return collection.stream()
                .map(ItWorkAdDtoMapper::toDto)
                .toList();
    }
    private Set<ItWorkAd> workingTimeSearch(WorkingTime workingTime){
        if(workingTime != null) {
            return adRepository.findAllByWorkingTime(workingTime);
        }
        else return null;
    }
    private Set<ItWorkAd> workFromHomeSearch(Boolean workFromHome){
        if(workFromHome != null) {
            return adRepository.findAllByWorkFromHome(workFromHome);
        }
        else return null;
    }

    private void addToSearchResults(Set<ItWorkAd> searchAds, List<Set<ItWorkAd>> searchResults){
        if(searchAds != null){
            searchResults.add(searchAds);
        }
    }

    public void addItWorkAd(ItWorkAdSaveDto adSaveDto, String subcategory) {
        ItWorkAd ad = new ItWorkAd();
        EntityMapper.mapSaveDtoToEntity(ad,
                adSaveDto,
                userRepository,
                subcategoryRepository,
                fileStorageService,
                subcategory);
        String workingTimeVal = adSaveDto.getWorkingTime();
        if(!workingTimeVal.equals("empty")){
            ad.setWorkingTime(WorkingTime.valueOf(workingTimeVal));
        }
        adRepository.save(ad);
    }

    public void deleteItWorkAd(long id) {
        adRepository.deleteById(id);
    }
}
