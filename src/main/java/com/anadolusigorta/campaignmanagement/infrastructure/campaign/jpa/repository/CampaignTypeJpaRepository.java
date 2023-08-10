package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CampaignTypeJpaRepository extends JpaRepository<CampaignTypeEntity, Long> {
    List<CampaignTypeEntity> findByCompanyId(Long companyId);
    Optional<CampaignTypeEntity> findByName(String name);
}
