package com.mathinjection.authservice.service;

import com.mathinjection.authservice.model.User;
import com.mathinjection.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(User userToPersist) {
        return userRepository.save(userToPersist);
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(new StringBuilder().append("User with id: ").append(id).append(" not found").toString()));
    }

    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(new StringBuilder().append("User with username: ").append(username).append(" not found").toString()));
    }

    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(new StringBuilder().append("User with email: ").append(email).append(" not found").toString()));
    }
}
