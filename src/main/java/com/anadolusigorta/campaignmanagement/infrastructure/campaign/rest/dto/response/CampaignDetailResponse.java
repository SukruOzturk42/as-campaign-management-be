package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDetailResponse {

    private Long id;

    private String referenceTypeDescription;

    private Long referenceTypeId;

    private String campaignLink;

    private String fileName;

    private LocalDateTime createdDate;

    private Long campaignId;

    private String campaignText;

    public static CampaignDetailResponse fromModel(CampaignDetail campaignDetail) {
        return CampaignDetailResponse.builder()
                .id(campaignDetail.getId())
                .referenceTypeDescription(campaignDetail.getReferenceType().getDescription())
                .referenceTypeId(campaignDetail.getReferenceType().getId())
                .campaignLink(campaignDetail.getCampaignLink())
                .fileName(campaignDetail.getFile() != null ? campaignDetail.getFile().getFileName() : null)
                .createdDate(campaignDetail.getCreatedDate())
                .campaignId(campaignDetail.getCampaignId())
                .campaignText(campaignDetail.getCampaignText())
                .build();
    }

    public static List<CampaignDetailResponse> fromListOfModel(List<CampaignDetail> campaignDetails) {
        return campaignDetails.stream().map(CampaignDetailResponse::fromModel).collect(Collectors.toList());
    }

}
