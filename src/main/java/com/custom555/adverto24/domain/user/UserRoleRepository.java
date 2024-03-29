package com.custom555.adverto24.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    Optional<UserRole> findByName(String name);
}
