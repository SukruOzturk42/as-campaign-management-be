package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignGoal;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.jpa.entity.CampaignGoalTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.jpa.entity.CampaignPolicyTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignsalechannel.jpa.entity.CampaignSaleChannelTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.entity.ReferenceTypeEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_goal")
@Where(clause = "is_active = 1")
public class CampaignGoalEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "goal_type_id", nullable = false)
    private CampaignGoalTypeEntity goalType;


    @ManyToOne
    @JoinColumn(name = "reference_type_id")
    private ReferenceTypeEntity referenceType;

    @Column(name = "budget_goal")
    private Long amountGoal;

    @Column(name = "policy_goal")
    private Long policyGoal;

    @Column(name = "insured_goal")
    private Long insuredGoal;

    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private CampaignEntity campaign;

    @Column(name = "is_active")
    private Boolean isActive = true;

    public CampaignGoal toModel() {
        return CampaignGoal.builder()
                .id(super.getId())
                .goalType(goalType.toModel())
                .referenceType(referenceType!=null?referenceType.toModel():null)
                .amountGoal(amountGoal)
                .updatedAt(getUpdatedAt())
                .policyGoal(policyGoal)
                .campaignId(campaign.getId())
                .insuredGoal(insuredGoal)
                .build();
    }

}
