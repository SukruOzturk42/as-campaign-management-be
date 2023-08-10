package com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatus;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatusEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_status")
public class CampaignStatusEntity extends AbstractEntity {

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_type", nullable = false)
    private CampaignStatusEnum campaignStatus;

    @Column(name = "code", nullable = false)
    private Long code;

    public CampaignStatus toModel() {
        return CampaignStatus.builder()
                .id(super.getId())
                .name(name)
                .status(campaignStatus)
                .code(code)
                .build();
    }

    public static CampaignStatusEntity fromModel(CampaignStatus campaignStatus) {
        return CampaignStatusEntity.builder()
                .name(campaignStatus.getName())
                .campaignStatus(campaignStatus.getStatus())
                .code(campaignStatus.getCode())
                .build();
    }

}
