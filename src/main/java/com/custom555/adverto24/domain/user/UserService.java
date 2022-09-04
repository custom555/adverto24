package com.custom555.adverto24.domain.user;

import com.custom555.adverto24.domain.user.dto.UserCredentialsDto;
import com.custom555.adverto24.domain.user.dto.UserDtoMapper;
import com.custom555.adverto24.domain.user.dto.UserRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserCredentialsDto> findCredentialsByEmail(String email){
        return userRepository.findByEmail(email)
                .map(UserDtoMapper::mapToCredentialsDto);
    }
    @Transactional
    public void deleteUserByEmail(String email){
        if(isCurrentAdmin())
            userRepository.deleteByEmail(email);
    }
    boolean isCurrentAdmin(){
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }
    @Transactional
    public void register(UserRegistrationDto registration){
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setTelephoneNo(registration.getTelephoneNo());
        user.setCity(registration.getCity());
        String passwordHash = passwordEncoder.encode(registration.getPassword());
        user.setPassword(passwordHash);
        Optional<UserRole> userRole = userRoleRepository.findByName("USER");
        userRole.ifPresentOrElse(role -> user.getRoles().add(role), ()-> {
            throw new NoSuchElementException();
        });
        userRepository.save(user);
    }

    public Optional<UserRegistrationDto> findUserById(Long id) {
        return userRepository.findById(id)
                .map(UserDtoMapper::mapToRegistrationDto);
    }
}
