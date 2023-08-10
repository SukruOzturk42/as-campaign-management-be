package com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignApprovalStatus;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignApprovalStatusEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_approval_status")
public class CampaignApprovalStatusEntity extends AbstractEntity {

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "campaign_approval_status")
    private CampaignApprovalStatusEnum campaignApprovalStatus;

    @Column(name = "code", nullable = false)
    private Long code;

    public CampaignApprovalStatus toModel() {
        return CampaignApprovalStatus.builder()
                .id(super.getId())
                .name(name)
                .approvalStatus(campaignApprovalStatus)
                .code(code)
                .build();
    }

    public static CampaignApprovalStatusEntity fromModel(CampaignApprovalStatus campaignApprovalStatus) {
        return CampaignApprovalStatusEntity.builder()
                .name(campaignApprovalStatus.getName())
                .campaignApprovalStatus(campaignApprovalStatus.getApprovalStatus())
                .code(campaignApprovalStatus.getCode())
                .build();
    }

}
