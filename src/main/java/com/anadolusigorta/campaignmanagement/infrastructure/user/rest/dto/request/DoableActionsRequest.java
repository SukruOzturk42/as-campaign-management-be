package com.anadolusigorta.campaignmanagement.infrastructure.user.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoableActionsRequest {

    private Long userRoleId;

    private Long campaignStatusId;

}
