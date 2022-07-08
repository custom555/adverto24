package com.custom555.adverto24.domain.user;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name= "user_role")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}
