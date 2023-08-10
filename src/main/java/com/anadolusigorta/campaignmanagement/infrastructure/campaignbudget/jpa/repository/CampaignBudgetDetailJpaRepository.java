package com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.jpa.entity.CampaignBudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignBudgetDetailJpaRepository extends JpaRepository<CampaignBudgetEntity, Long> {
}
