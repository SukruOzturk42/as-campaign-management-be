package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.Campaign;
import com.anadolusigorta.campaignmanagement.domain.operator.ConjunctionOperatorEnum;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignResponse {

    private Long id;

    private CampaignInformationResponse campaignInformation;

    private List<CampaignRuleGroupResponse> campaignRuleGroups;





    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CampaignAttributeResponse {

        private Long attributeId;
        private Long structureId;
        private OperatorEnum operator;
        private AttributeTypeEnum type;
        private List<String> value;
    }



    public static CampaignResponse fromModel(Campaign campaign) {
        return CampaignResponse.builder()
                .id(campaign.getId())
                .campaignInformation(CampaignInformationResponse
                        .fromModel(campaign.getCampaignInformation()))
                .campaignRuleGroups(CampaignRuleGroupResponse
                        .fromListOfModel(campaign.getRuleGroups()))
                .build();
    }

    public static List<CampaignResponse> fromListOfModel(List<Campaign> campaigns) {
        return campaigns.stream().map(CampaignResponse::fromModel).collect(Collectors.toList());
    }


}
