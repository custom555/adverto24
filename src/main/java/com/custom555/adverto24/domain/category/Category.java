package com.custom555.adverto24.domain.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private String url;
    private String imgName;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private List<Subcategory> subcategories = new ArrayList<>();
}
