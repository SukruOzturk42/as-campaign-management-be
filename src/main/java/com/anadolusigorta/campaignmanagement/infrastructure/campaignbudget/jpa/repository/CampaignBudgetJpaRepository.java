package com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.jpa.repository;

import com.anadolusigorta.campaignmanagement.domain.campaignbudget.model.CampaignBudget;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.jpa.entity.CampaignBudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CampaignBudgetJpaRepository extends JpaRepository<CampaignBudgetEntity, Long> {

    Optional<List<CampaignBudgetEntity>> findAllByCampaignId(Long campaignId);
}
