package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignInformation;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.entity.CampaignApprovalStatusEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.entity.CampaignStatusEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity.RoleAuthorizationActionEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_information")
public class CampaignInformationEntity extends AbstractEntity {


    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private CampaignEntity campaign;

    @ManyToOne
    @JoinColumn(name = "campaign_version_id", nullable = false)
    private CampaignVersionEntity campaignVersion;

    @ManyToOne
    @JoinColumn(name = "campaign_group_id")
    private CampaignGroupEntity campaignGroup;

    @ManyToOne
    @JoinColumn(name = "action_id", nullable = false)
    private RoleAuthorizationActionEntity roleAuthorizationAction;

    @Column(name = "campaign_name", nullable = false)
    private String campaignName;

    @Column(name = "campaign_title")
    private String campaignTitle;

    @ManyToOne
    @JoinColumn(name = "campaign_type_id", nullable = false)
    private CampaignTypeEntity campaignType;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "campaign_status_id", nullable = false)
    private CampaignStatusEntity campaignStatus;


    @ManyToOne
    @JoinColumn(name = "campaign_approval_status_id", nullable = false)
    private CampaignApprovalStatusEntity campaignApprovalStatus;

    @Column(name = "tags")
    private String tags;

    @Column(name = "action_description", length = 2000)
    private String actionDescription;

    @Column(name = "short_description", length = 2000)
    private String shortDescription;

    @Column(name = "campaign_start_date")
    private LocalDateTime campaignStartDate;

    @Column(name = "campaign_end_date")
    private LocalDateTime campaignEndDate;

    @Column(name = "version_start_date")
    private LocalDateTime versionStartDate;

    @Column(name = "campaign_owner")
    private String campaignOwner;

    @Column(name = "has_customer_limit")
    private Boolean hasCustomerLimit;

    @Column(name = "customer_limit_size")
    private Integer customerLimitSize;

    @Column(name = "is_triggered_reward_send")
    private Boolean isTriggeredRewardSend=Boolean.FALSE;

    @Column(name = "is_started_reward_send")
    private Boolean isStartedRewardSend=Boolean.TRUE;

    public CampaignInformation toModel() {
        return CampaignInformation.builder()
                .id(getId())
                .campaignId(campaign.getId())
                .campaignGroup(campaignGroup!=null?campaignGroup.toModel():null)
                .campaignStatus(campaignStatus.toModel())
                .campaignApprovalStatus(campaignApprovalStatus.toModel())
                .version(campaignVersion.getVersion())
                .actionId(roleAuthorizationAction.getId())
                .campaignName(campaignName)
                .campaignTitle(campaignTitle)
                .campaignType(campaignType.toModel())
                .tags(tags!=null?Arrays.asList(tags.split(",")):null)
                .campaignStartDate(campaignStartDate)
                .campaignEndDate(campaignEndDate)
                .versionStartDate(versionStartDate)
                .createDate(campaign.getCreatedAt())
                .campaignOwner(campaignOwner)
                .actionDescription(actionDescription)
                .shortDescription(shortDescription)
                .hasCustomerLimit(hasCustomerLimit)
                .customerLimitSize(customerLimitSize)
                .isStartedRewardSend(isStartedRewardSend!=null?isStartedRewardSend:Boolean.TRUE)
                .isTriggeredRewardSend(isTriggeredRewardSend!=null?isTriggeredRewardSend:Boolean.FALSE)
                .build();
    }

}
