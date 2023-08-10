package com.anadolusigorta.campaignmanagement.infrastructure.user.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.user.model.RoleAuthorizationAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleAuthorizationActionResponse {

    private Long id;

    private String userRoleName;

    private Long campaignStatusId;

    private Long campaignApprovalStatusId;

    private String name;

    private String description;

    private String type;

    public static RoleAuthorizationActionResponse fromModel(RoleAuthorizationAction roleAuthorizationAction) {
        return RoleAuthorizationActionResponse.builder()
                .id(roleAuthorizationAction.getId())
                .userRoleName(roleAuthorizationAction.getUserRoleName())
                .campaignStatusId(roleAuthorizationAction.getCampaignStatusId())
                .campaignApprovalStatusId(roleAuthorizationAction.getCampaignApprovalStatusId())
                .name(roleAuthorizationAction.getName())
                .description(roleAuthorizationAction.getDescription())
                .type(roleAuthorizationAction.getType())
                .build();
    }

    public static List<RoleAuthorizationActionResponse> fromListOfModel(List<RoleAuthorizationAction> roleAuthorizationActions) {
        return roleAuthorizationActions.stream().map(RoleAuthorizationActionResponse::fromModel).collect(Collectors.toList());
    }

}
