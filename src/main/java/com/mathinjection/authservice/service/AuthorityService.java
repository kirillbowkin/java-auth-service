package com.mathinjection.authservice.service;

import com.mathinjection.authservice.entity.AuthorityEntity;
import com.mathinjection.authservice.model.FlatAuthorityModel;
import com.mathinjection.authservice.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public List<FlatAuthorityModel> getAll() {
        return authorityRepository.findAll().stream().map(FlatAuthorityModel::EntityToModel).collect(Collectors.toList());
    }

    public FlatAuthorityModel findAuthorityByName(String authorityName) {
        return FlatAuthorityModel.EntityToModel(authorityRepository.findByName(authorityName).orElseThrow(() -> new NotFoundException(new StringBuilder().append("Authority with name ").append(authorityName).append(" was not found").toString())));
    }

    public FlatAuthorityModel findAuthorityById(UUID authorityId) {
        return FlatAuthorityModel.EntityToModel(authorityRepository.findById(authorityId).orElseThrow(() -> new NotFoundException(new StringBuilder().append("Authority with id ").append(authorityId).append(" was not found").toString())));

    }

    public FlatAuthorityModel addAuthority(String authorityName) {
        return FlatAuthorityModel.EntityToModel(authorityRepository.save(new AuthorityEntity().setName(authorityName)));
    }

    public void deleteAuthorityById(UUID authorityId) {
        authorityRepository.deleteById(authorityId);
    }
}
