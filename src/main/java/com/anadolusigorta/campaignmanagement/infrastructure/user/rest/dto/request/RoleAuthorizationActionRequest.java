package com.anadolusigorta.campaignmanagement.infrastructure.user.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.user.model.RoleAuthorizationAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleAuthorizationActionRequest {

    private String userRoleName;

    private Long campaignStatusId;

    private Long campaignApprovalStatusId;

    public RoleAuthorizationAction toModel() {
        return RoleAuthorizationAction.builder()
                .userRoleName(userRoleName)
                .campaignStatusId(campaignStatusId)
                .campaignApprovalStatusId(campaignApprovalStatusId)
                .build();
    }

}
