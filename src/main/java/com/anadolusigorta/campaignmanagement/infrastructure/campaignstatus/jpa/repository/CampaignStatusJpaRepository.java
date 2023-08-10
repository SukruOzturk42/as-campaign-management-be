package com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.repository;

import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatusEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.entity.CampaignStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CampaignStatusJpaRepository extends JpaRepository<CampaignStatusEntity, Long> {

    Optional<CampaignStatusEntity> findByCode(Long code);
    Optional<CampaignStatusEntity> findByName(String name);
    Optional<CampaignStatusEntity> findByCampaignStatus(CampaignStatusEnum statusName);



}
