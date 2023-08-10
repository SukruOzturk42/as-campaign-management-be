package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import com.anadolusigorta.campaignmanagement.domain.user.model.RoleAuthorizationAction;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignApproval {

    private Long id;

    private String username;

    private RoleAuthorizationAction roleAuthorizationAction;

    private Long campaignId;

    private Long campaignVersionId;

}
