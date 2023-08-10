package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignApproval;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity.RoleAuthorizationActionEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_state_history")
public class CampaignStateHistory extends AbstractEntity {

    @Column(name = "username",length = 50)
    private String username;


    @ManyToOne
    @JoinColumn(name = "campaign_version_id", nullable = false)
    private CampaignVersionEntity campaignVersion;

    @ManyToOne
    @JoinColumn(name = "role_authorizatio_action_id", nullable = false)
    private RoleAuthorizationActionEntity roleAuthorizationAction;

    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private CampaignEntity campaign;

    public CampaignApproval toModel() {
        return CampaignApproval.builder()
                .id(super.getId())
                .roleAuthorizationAction(roleAuthorizationAction.toModel())
                .username(username)
                .build();
    }


}
