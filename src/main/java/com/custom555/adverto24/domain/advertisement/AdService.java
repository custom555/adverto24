package com.custom555.adverto24.domain.advertisement;

import com.custom555.adverto24.domain.advertisement.category.electronics.telephone.dto.TelephoneAdSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdService {
    private final AdRepository adRepository;

    public List<AdDto> findAllAdsByOwnerId(long id) {
      List<Ad> adList = adRepository.findAllByOwner_id(id);
      return mapToDto(adList);

    }
    public List<AdDto> findAllPromotedAds() {
        List<Ad> adList = adRepository.findAllByPromotedIsTrue();
        return mapToDto(adList);
    }
    private List<AdDto> mapToDto(Collection<Ad> collection){
        return collection
                .stream()
                .map(ad -> AdDtoMapper.toDto(ad,new AdDto()))
                .toList();
    }
    public List<AdDto> findAllAdsByOwnerEmail(String email) {
        List<Ad> adList = adRepository.findAllByOwner_email(email);
        return mapToDto(adList);
    }
}
