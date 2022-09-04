package com.custom555.adverto24.domain.user.dto;

import com.custom555.adverto24.domain.user.User;
import com.custom555.adverto24.domain.user.UserRole;

import java.util.Set;
import java.util.stream.Collectors;
public class UserDtoMapper {
    public static UserCredentialsDto mapToCredentialsDto(User user){
        String email = user.getEmail();
        String password = user.getPassword();
        Set<String> roles = user.getRoles().stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        return new UserCredentialsDto(email,password,roles);
    }
    public static UserRegistrationDto mapToRegistrationDto(User user){
        UserRegistrationDto dto = new UserRegistrationDto();

        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setTelephoneNo(user.getTelephoneNo());
        dto.setCity(user.getCity());

        return dto;
    }
}
