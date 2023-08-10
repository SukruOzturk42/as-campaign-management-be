package com.anadolusigorta.campaignmanagement.infrastructure.budgetitemtype.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.budgetitemtype.model.BudgetITemType;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignType;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "budget_item_type")
public class BudgetItemTypeEntity extends AbstractEntity {

    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;

    @Column(name = "description", nullable = false, length = 400)
    private String description;


    public BudgetITemType toModel(){
        return BudgetITemType.builder()
                .id(super.getId())
                .name(name)
                .description(description)
                .build();
    }

    public static BudgetItemTypeEntity fromModel(CampaignType campaignType) {
        return BudgetItemTypeEntity.builder()
                .name(campaignType.getName())
                .description(campaignType.getDescription())
                .build();
    }

}
