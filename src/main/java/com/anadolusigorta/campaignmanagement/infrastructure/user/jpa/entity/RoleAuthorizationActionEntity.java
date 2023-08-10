package com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.user.model.RoleAuthorizationAction;
import com.anadolusigorta.campaignmanagement.infrastructure.action.jpa.entity.RoleActionEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.entity.CampaignApprovalStatusEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.entity.CampaignStatusEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role_authorization_action")
public class RoleAuthorizationActionEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private UserRoleEntity userRole;

    @ManyToOne
    @JoinColumn(name = "campaign_status_id")
    private CampaignStatusEntity campaignStatus;

    @ManyToOne
    @JoinColumn(name = "campaign_next_status_id")
    private CampaignStatusEntity campaignNextStatus;

    @ManyToOne
    @JoinColumn(name = "campaign_next_approval_status_id")
    private CampaignApprovalStatusEntity campaignNextApprovalStatus;

    @ManyToOne
    @JoinColumn(name = "campaign_old_version_status_id")
    private CampaignStatusEntity campaignOldVersionStatus;

    @ManyToOne
    @JoinColumn(name = "campaign_old_version_approval_status_id")
    private CampaignApprovalStatusEntity campaignOldApprovalStatus;

    @ManyToOne
    @JoinColumn(name = "campaign_approval_status_id")
    private CampaignApprovalStatusEntity campaignApprovalStatus;

    @ManyToOne
    @JoinColumn(name = "role_action_id")
    private RoleActionEntity roleAction;

    @Column(name = "is_initial_action")
    private Boolean isInitialAction;

    @Column(name = "type")
    private String type;

    public RoleAuthorizationAction toModel() {
        return RoleAuthorizationAction.builder()
                .id(super.getId())
                .userRoleName(userRole.getName())
                .campaignStatusId(campaignStatus.getId())
                .campaignApprovalStatusId(campaignApprovalStatus.getId())
                .name(roleAction.getName())
                .description(roleAction.getDescription())
                .type(type)
                .build();
    }

}
