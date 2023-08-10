package com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.user.model.UserRole;
import com.anadolusigorta.campaignmanagement.domain.user.port.UserRoleRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskSubStateTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity.UserRoleEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.repository.UserRoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleRepositoryJpaAdapter implements UserRoleRepository {

    private final UserRoleJpaRepository userRoleJpaRepository;


    @Override
    public List<UserRole> getAllUserRole() {

        return userRoleJpaRepository.findAll().stream().map(UserRoleEntity::toModel)
                .collect(Collectors.toList());
    }
}
