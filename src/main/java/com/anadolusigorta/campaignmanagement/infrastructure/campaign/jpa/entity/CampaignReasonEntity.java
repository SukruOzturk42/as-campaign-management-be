package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;


import com.anadolusigorta.campaignmanagement.domain.campaign.model.Campaign;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignReason;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_reason")
@Where(clause = "is_active = 1")
public class CampaignReasonEntity extends AbstractEntity {

    @Column(name = "deciding_organization")
    private String decidingOrganization;

    @Column(name = "decision_number")
    private String decisionNumber;

    @Column(name = "decision_description")
    private String decisionDescription;

    @Column(name = "decision_date")
    private LocalDateTime decisionDate;

    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private CampaignEntity campaign;

    @Column(name = "is_active")
    private Boolean isActive = true;

    public CampaignReason toModel() {
        return CampaignReason.builder()
                .id(super.getId())
                .decidingOrganization(decidingOrganization)
                .decisionNumber(decisionNumber)
                .decisionDescription(decisionDescription)
                .decisionDate(decisionDate)
                .createdDate(super.getCreatedAt())
                .campaignId(campaign.getId())
                .build();
    }


}
