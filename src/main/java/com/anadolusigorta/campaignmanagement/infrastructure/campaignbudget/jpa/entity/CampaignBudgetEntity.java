package com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaignbudget.model.CampaignBudget;
import com.anadolusigorta.campaignmanagement.domain.campaignbudget.model.CampaignBudgetDetail;
import com.anadolusigorta.campaignmanagement.infrastructure.budgetitemtype.jpa.entity.BudgetItemTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "campaign_budget")
@Where(clause = "is_active = 1")
public class CampaignBudgetEntity extends AbstractEntity {

    private LocalDateTime budgetDate;

    @OneToOne
    @JoinColumn(name = "budgetItem_id", referencedColumnName = "id")
    private BudgetItemTypeEntity budgetItem;

    private Long budgetAmount;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private CampaignEntity campaign;

    @OneToMany(mappedBy="campaignBudget" ,cascade = CascadeType.ALL)
    private List<CampaignBudgetDetailEntity> campaignBudgeDetailtList = new ArrayList<>();

    public CampaignBudget toModel(){
        return CampaignBudget.builder()
                .id(getId())
                .budgetItemType(getBudgetItem().toModel())
                .createdAt(getCreatedAt())
                .budgetDate(getBudgetDate())
                .budgetAmount(getBudgetAmount())
                .campaignId(getCampaign().getId())
                .campaignBudgetDetailList(getCampaignBudgeDetailtList().stream().map(CampaignBudgetDetailEntity::toModel).collect(Collectors.toList()))
                .build();
    }

}
