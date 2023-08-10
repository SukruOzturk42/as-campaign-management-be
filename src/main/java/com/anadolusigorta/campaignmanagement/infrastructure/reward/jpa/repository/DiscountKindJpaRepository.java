package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.DiscountKindEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.GiftKindEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountKindJpaRepository extends JpaRepository<DiscountKindEntity, Long> {


	Optional<DiscountKindEntity> findByName(String name);
}
