package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_approval_notification_detail")
public class CampaignApprovalNotificationDetailEntity extends AbstractEntity {

    @Column(name = "campaign_id")
    private Long campaignId;

    @Column(name = "campaign_information_id")
    private Long campaignInformationId;

    @Column(name = "version")
    private Long version;

    @Column(name = "notification_task_id")
    private String notificationTaskId;

    @Column(name = "approving_notification_task_id")
    private String approvingNotificationTaskId;

    @Column(name = "approve_action_date")
    private LocalDateTime approveActionDate;

    @Column(name = "publish_action_date")
    private LocalDateTime publishActionDate;

    @Column(name = "reject_action_date")
    private LocalDateTime rejectActionDate;

    @Column(name = "description")
    private String description;

    @Column(name = "approval_action_owner")
    private String approvalActionOwner;

    @Column(name = "approval_action_owner_email")
    private String approvalActionOwnerEmail;

    @Column(name = "reject_action_owner")
    private String rejectActionOwner;

    @Column(name = "reject_action_owner_email")
    private String rejectActionOwnerEmail;

    @Column(name = "publish_action_owner")
    private String publishActionOwner;

    @Column(name = "publish_action_owner_email")
    private String publishActionOwnerEmail;

}
