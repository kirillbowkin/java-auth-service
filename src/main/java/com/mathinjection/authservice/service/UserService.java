package com.mathinjection.authservice.service;

import com.mathinjection.authservice.entity.UserEntity;
import com.mathinjection.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity save(UserEntity userToPersist) {
        return userRepository.save(userToPersist);
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(new StringBuilder().append("User with id: ").append(id).append(" not found").toString()));
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(new StringBuilder().append("User with username: ").append(username).append(" not found").toString()));
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(new StringBuilder().append("User with email: ").append(email).append(" not found").toString()));
    }
}
