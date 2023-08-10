package com.anadolusigorta.campaignmanagement.infrastructure.campaignsalechannel.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaignsalechannel.model.CampaignSaleChannelType;
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
public class CampaignSaleChannelTypeResponse {

    private Long id;

    private String name;

    private String description;

    public static CampaignSaleChannelTypeResponse fromModel(CampaignSaleChannelType campaignSaleChannelType) {
        return CampaignSaleChannelTypeResponse.builder()
                .id(campaignSaleChannelType.getId())
                .name(campaignSaleChannelType.getName())
                .description(campaignSaleChannelType.getDescription())
                .build();
    }

    public static List<CampaignSaleChannelTypeResponse> fromListOfModel(List<CampaignSaleChannelType> campaignSaleChannelTypes) {
        return campaignSaleChannelTypes.stream().map(CampaignSaleChannelTypeResponse::fromModel).collect(Collectors.toList());
    }
}
