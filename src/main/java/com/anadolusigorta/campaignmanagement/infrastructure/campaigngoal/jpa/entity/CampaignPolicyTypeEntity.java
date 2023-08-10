package com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignPolicyType;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_policy_type")
public class CampaignPolicyTypeEntity extends AbstractEntity {

    private Long policyCode;

    private Long policyType;

    private String name;

    private String description;

    public CampaignPolicyType toModel() {
        return CampaignPolicyType.builder()
                .id(super.getId())
                .policyCode(policyCode)
                .policyType(policyType)
                .name(name)
                .description(description)
                .build();
    }

}
