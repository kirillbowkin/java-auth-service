package com.mathinjection.authservice.service;

import com.mathinjection.authservice.entity.RoleEntity;
import com.mathinjection.authservice.entity.UserEntity;
import com.mathinjection.authservice.model.UserModel;
import com.mathinjection.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<UserModel> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(UserModel::EntityToModel).collect(Collectors.toList());
    }

    public UserModel findModelById(UUID id) {
        return UserModel.EntityToModel(userRepository.findUserById(id).orElseThrow(() -> new UsernameNotFoundException(new StringBuilder().append("User with id: ").append(id).append(" not found").toString())));
    }

    public UserEntity getModelById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(new StringBuilder().append("User with id: ").append(id).append(" not found").toString()));
    }

    public UserEntity findEntityByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(new StringBuilder().append("User with username: ").append(username).append(" not found").toString()));
    }

    public UserModel findModelByUsername(String username) {
        return UserModel.EntityToModel(userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(new StringBuilder().append("User with username: ").append(username).append(" not found").toString())));
    }

    public void addRoleToUser(UUID user_id, UUID role_id) {
        userRepository.addRoleToUser(user_id, role_id);
    }

    public void deleteRoleFromUser(UUID user_id, UUID role_id) {
        userRepository.deleteRoleFromUser(user_id, role_id);
    }

    public UserEntity findEntityByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(new StringBuilder().append("User with email: ").append(email).append(" not found").toString()));
    }

    public UserModel findModelByEmail(String email) {
        return UserModel.EntityToModel(userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(new StringBuilder().append("User with email: ").append(email).append(" not found").toString())));
    }

}
