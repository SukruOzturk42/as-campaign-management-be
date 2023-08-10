package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.RewardGiftTicketTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RewardGiftTicketTypeJpaRepository extends JpaRepository<RewardGiftTicketTypeEntity, Long> {

    Optional<RewardGiftTicketTypeEntity> findByName(String name);

}
