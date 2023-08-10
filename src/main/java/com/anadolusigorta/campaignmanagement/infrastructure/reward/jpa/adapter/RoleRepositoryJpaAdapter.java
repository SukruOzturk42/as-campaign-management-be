package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardRole;
import com.anadolusigorta.campaignmanagement.domain.reward.port.RewardRoleRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.RewardRoleEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.RewardRoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleRepositoryJpaAdapter implements RewardRoleRepository {
    private  final RewardRoleJpaRepository rewardRoleJpaRepository;


    @Override
    public List<RewardRole> findAll() {
        return rewardRoleJpaRepository.findAll().stream()
                .map(RewardRoleEntity::toModel)
                .collect(Collectors.toList());
    }


}
