package com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.jpa.entity.CampaignPolicyTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignPolicyTypeJpaRepository extends JpaRepository<CampaignPolicyTypeEntity, Long> {

}
