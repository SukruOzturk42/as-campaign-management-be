package com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignPolicyType;
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
public class CampaignPolicyTypeResponse {

    private Long id;

    private Long policyCode;

    private Long policyType;

    private String name;

    private String description;

    public static CampaignPolicyTypeResponse fromModel(CampaignPolicyType campaignPolicyType) {
        return CampaignPolicyTypeResponse.builder()
                .id(campaignPolicyType.getId())
                .policyCode(campaignPolicyType.getPolicyCode())
                .policyType(campaignPolicyType.getPolicyType())
                .name(campaignPolicyType.getName())
                .description(campaignPolicyType.getDescription())
                .build();
    }

    public static List<CampaignPolicyTypeResponse> fromListOfModel(List<CampaignPolicyType> campaignPolicyTypeList) {
        return campaignPolicyTypeList.stream().map(CampaignPolicyTypeResponse::fromModel).collect(Collectors.toList());
    }

}
