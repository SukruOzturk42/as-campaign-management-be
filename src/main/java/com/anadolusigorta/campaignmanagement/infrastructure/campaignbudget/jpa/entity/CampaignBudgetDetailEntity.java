package com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaignbudget.model.CampaignBudget;
import com.anadolusigorta.campaignmanagement.domain.campaignbudget.model.CampaignBudgetDetail;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "campaign_budget_detail")
public class CampaignBudgetDetailEntity extends AbstractEntity {

    private String updater;

    private String description;
    @ManyToOne
    @JoinColumn(name="campaign_budget_id", nullable=true)
    private CampaignBudgetEntity campaignBudget;

    public CampaignBudgetDetail toModel(){
        return CampaignBudgetDetail.builder()
                .id(getId())
                .description(getDescription())
                .updater(getUpdater())
                .createdAt(getCreatedAt())
                .build();
    }
}

