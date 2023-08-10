package com.anadolusigorta.campaignmanagement.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleAuthorizationAction {

    private Long id;

    private String userRoleName;

    private Long campaignStatusId;

    private Long campaignApprovalStatusId;

    private String name;

    private String description;

    private String type;

}
