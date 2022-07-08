package com.custom555.adverto24.domain.user.dto;

import com.custom555.adverto24.domain.user.User;
import com.custom555.adverto24.domain.user.UserRole;

import java.util.Set;
import java.util.stream.Collectors;

public class UserCredentialsDtoMapper {
    public static UserCredentialsDto map(User user){
        String email = user.getEmail();
        String password = user.getPassword();
        Set<String> roles = user.getRoles().stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        return new UserCredentialsDto(email,password,roles);
    }
}
