package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.RewardCompanyInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RewardCompanyInformationJpaRepository extends JpaRepository<RewardCompanyInformationEntity, Long> {

    Optional<RewardCompanyInformationEntity> findByName(String name);

}
