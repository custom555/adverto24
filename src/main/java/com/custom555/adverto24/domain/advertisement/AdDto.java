package com.custom555.adverto24.domain.advertisement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdDto {
    private Long id;
    private String name;
    private String body;
    private String img;
    private Double price;
    private Long ownerId;
    private String categoryUrl;
    private String subcategoryUrl;
}
