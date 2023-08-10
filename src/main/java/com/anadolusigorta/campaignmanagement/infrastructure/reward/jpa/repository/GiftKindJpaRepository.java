package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository;


import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.GiftKindEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GiftKindJpaRepository extends JpaRepository<GiftKindEntity, Long> {

	Optional<GiftKindEntity> findByName(String name);
}
