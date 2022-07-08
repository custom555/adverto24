package com.custom555.adverto24.domain.advertisment;

import com.custom555.adverto24.domain.category.Category;
import com.custom555.adverto24.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Advertisment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String body;
    private LocalDateTime created;
    private boolean promoted;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
