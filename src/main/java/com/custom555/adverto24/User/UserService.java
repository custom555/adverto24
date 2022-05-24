package com.custom555.adverto24.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    void reqisterUser(User user){
        userRepository.save(user);
    }
}
