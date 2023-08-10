package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignDetail;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignInformation;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDetailResponse {

    private Long campaignId;
    private LocalDateTime campaignStartDate;
    private LocalDateTime campaignEndDate;
    private List<DescriptionInfo> descriptionInfos;

    public static CampaignDetailResponse fromModel(CampaignInformation campaignInformation,
                                                         List<CampaignDetail> campaignDetails){
        return CampaignDetailResponse.builder().campaignId(campaignInformation.getCampaignId())
                .campaignEndDate(campaignInformation.getCampaignEndDate())
                .campaignStartDate(campaignInformation.getCampaignStartDate())
                .descriptionInfos(DescriptionInfo.toModel(campaignDetails))
                .build();
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DescriptionInfo {
        private String salesChannelId;
        private String description;
        private String campaignImageCMID;
        
        public static List<DescriptionInfo> toModel(List<CampaignDetail> campaignDetails){
            return campaignDetails.stream()
                    .map(p -> new DescriptionInfo(p.getReferenceType().getName(), p.getCampaignText(), p.getFilePid()))
                    .collect(Collectors.toList());
        }
    }
}
