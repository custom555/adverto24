package com.custom555.adverto24.domain.advertisement;

import com.custom555.adverto24.domain.category.Subcategory;
import com.custom555.adverto24.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "advertisement")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ad_type",discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@Getter
@Setter
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String body;
    private LocalDate created;
    private boolean promoted;
    private String img;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;
}
