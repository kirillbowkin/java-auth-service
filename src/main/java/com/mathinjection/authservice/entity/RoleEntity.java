package com.mathinjection.authservice.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "roles")
@Accessors(chain = true)
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_authorities", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Collection<AuthorityEntity> authorities;

}
