package com.custom555.adverto24.domain.advertisement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@NoArgsConstructor
public class AdSaveDto {
    private String name;
    private String body;
    private MultipartFile img;
    private Double price;
    private String subcategory;
    private String owner;

}
