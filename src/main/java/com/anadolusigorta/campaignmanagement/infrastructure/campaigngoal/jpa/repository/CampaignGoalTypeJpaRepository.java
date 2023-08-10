package com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.jpa.entity.CampaignGoalTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignGoalTypeJpaRepository extends JpaRepository<CampaignGoalTypeEntity, Long> {

}
