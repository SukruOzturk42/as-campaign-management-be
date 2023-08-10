package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleInformation;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionOperationType;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignRuleGroupEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Immutable;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SALE_CAMPAIGN_VIEW")
@Immutable
public class SaleCampaignReportViewEntity implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "campaign_version")
    private Long campaignVersion;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "campaign_id")
    private Long campaignId;

    @Column(name = "campaign_information_id")
    private Long campaignInformationId;

    @Column(name = "campaign_name")
    private String campaignName;

    @Column(name = "campaign_type_description")
    private String campaignTypeDescription;

    @Column(name = "campaign_start_date")
    private LocalDateTime campaignStartDate;

    @Column(name = "campaign_end_date")
    private LocalDateTime campaignEndDate;

    @Column(name = "is_triggered_reward_send")
    private Boolean isTriggeredRewardSend;

    @Column(name = "is_started_reward_send")
    private Boolean isStartedRewardSend;

    @Column(name = "sale_count")
    private Long saleCount;

    @Column(name = "proposal_count")
    private Long proposalCount;


}
