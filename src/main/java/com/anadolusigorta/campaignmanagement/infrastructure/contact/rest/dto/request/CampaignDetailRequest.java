package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDetailRequest {
    @NotNull
    private Long campaignId;

    @NotNull
    private Long campaignVersion;

    @NotNull
    private Long ruleGroupId;


}
