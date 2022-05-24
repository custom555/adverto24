package com.custom555.adverto24.Advertisment;

import com.custom555.adverto24.Category.Category;
import com.custom555.adverto24.User.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Advertisment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String body;
    @NonNull
    private LocalDateTime created;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @NonNull
    private User owner;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @NonNull
    private Category category;
}
