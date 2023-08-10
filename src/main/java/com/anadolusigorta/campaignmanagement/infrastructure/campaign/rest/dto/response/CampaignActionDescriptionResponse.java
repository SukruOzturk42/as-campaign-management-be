package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;


import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignInformation;
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
public class CampaignActionDescriptionResponse {

    private Long id;
    private String description;

    private Long version;




    public static CampaignActionDescriptionResponse fromModel(CampaignInformation campaignInformation) {
        return CampaignActionDescriptionResponse.builder()
                .id(campaignInformation.getId())
                .description(campaignInformation.getActionDescription())
                .version(campaignInformation.getVersion())
                .build();
    }

    public static List<CampaignActionDescriptionResponse> fromListOfModel(List<CampaignInformation> campaigns) {
        return campaigns.stream()
                .map(CampaignActionDescriptionResponse::fromModel)
                .collect(Collectors.toList());
    }


}
