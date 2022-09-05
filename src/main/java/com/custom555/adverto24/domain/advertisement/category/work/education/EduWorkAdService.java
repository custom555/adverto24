package com.custom555.adverto24.domain.advertisement.category.work.education;

import com.custom555.adverto24.domain.advertisement.category.work.education.dto.EduWorkAdDto;
import com.custom555.adverto24.domain.advertisement.category.work.education.dto.EduWorkAdSaveDto;
import com.custom555.adverto24.domain.advertisement.enums.WorkingTime;
import com.custom555.adverto24.domain.category.SubcategoryRepository;
import com.custom555.adverto24.domain.user.UserRepository;
import com.custom555.adverto24.storage.FileStorageService;
import com.custom555.adverto24.utils.EntityMapper;
import com.custom555.adverto24.utils.SetIntersection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EduWorkAdService {
    private final EduWorkAdRepository adRepository;
    private final UserRepository userRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final FileStorageService fileStorageService;

    public List<EduWorkAdDto> findAllEduWorkAds() {
        List<EduWorkAd> allEduWorkAds = adRepository.findAll();
        return mapToDto(allEduWorkAds);
    }

    public List<EduWorkAdDto> findEduWorkAdsByParams(WorkingTime workingTime, Boolean workFromHome) {
        List<Set<EduWorkAd>> searchResults = new ArrayList<>();
        Set<EduWorkAd> allEduWorkAds = new HashSet<>(adRepository.findAll());
        searchResults.add(allEduWorkAds);

        Set<EduWorkAd> workingTimeSearchAds = workingTimeSearch(workingTime);
        addToSearchResults(workingTimeSearchAds, searchResults);

        Set<EduWorkAd> workFromHomeAds = workFromHomeSearch(workFromHome);
        addToSearchResults(workFromHomeAds, searchResults);

        Set<EduWorkAd> resultSet = SetIntersection.getIntersection(searchResults);
        return mapToDto(resultSet);
    }
    public Optional<EduWorkAdDto> findEduWorkAdById(long id) {
        return adRepository.findById(id).map(EduWorkAdDtoMapper::toDto);
    }
    private List<EduWorkAdDto> mapToDto(Collection<EduWorkAd> collection){
        return collection.stream()
                .map(EduWorkAdDtoMapper::toDto)
                .collect(Collectors.toList());
    }
    private Set<EduWorkAd> workingTimeSearch(WorkingTime workingTime){
        if(workingTime != null) {
            return adRepository.findAllByWorkingTime(workingTime);
        }
        else return null;
    }
    private Set<EduWorkAd> workFromHomeSearch(Boolean workFromHome){
        if(workFromHome != null) {
            return adRepository.findAllByWorkFromHome(workFromHome);
        }
        else return null;
    }

    private void addToSearchResults(Set<EduWorkAd> searchAds, List<Set<EduWorkAd>> searchResults){
        if(searchAds != null){
            searchResults.add(searchAds);
        }
    }

    public void addEduWorkAd(EduWorkAdSaveDto adSaveDto, String subcategory) {
        EduWorkAd ad = new EduWorkAd();
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

    public void deleteEduWorkAd(long id) {
        adRepository.deleteById(id);
    }
}
