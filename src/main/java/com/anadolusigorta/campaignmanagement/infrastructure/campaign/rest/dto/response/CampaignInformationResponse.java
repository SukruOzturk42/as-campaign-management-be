package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
public class CampaignInformationResponse {

    private Long campaignId;

    private Long actionId;

    private Long campaignStatusId;

    private Long campaignApprovalStatusId;

    private String campaignApprovalStatusName;

    private String campaignStatusName;

    private Long campaignGroupId;

    private Long campaignTypeId;

    private String campaignGroupName;

    private String campaignName;

    private String campaignTitle;

    private String campaignTypeName;

    private List<String> tags;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime campaignStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime campaignEndDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime versionStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createDate;

    private String campaignOwner;

    private Long version;

    private String shortDescription;

    private Boolean hasCustomerLimit;

    private Integer customerLimitSize;


    public static CampaignInformationResponse fromModel(CampaignInformation campaignInformation) {
        return CampaignInformationResponse.builder()
                .actionId(campaignInformation.getActionId())
                .campaignStatusId(campaignInformation.getCampaignStatus().getId())
                .campaignApprovalStatusId(campaignInformation.getCampaignApprovalStatus().getId())
                .campaignApprovalStatusName(campaignInformation.getCampaignApprovalStatus().getName())
                .campaignStatusName(campaignInformation.getCampaignEndDate().isBefore(LocalDateTime.now())
                            ? Constants.CLOSED_CAMPAIGN_ACTION_NAME
                            : campaignInformation.getCampaignStatus().getName())
                .version(campaignInformation.getVersion())
                .campaignId(campaignInformation.getCampaignId())
                .campaignGroupName(campaignInformation.getCampaignGroup() !=null ?
                        campaignInformation.getCampaignGroup().getName():null)
                .campaignName(campaignInformation.getCampaignName())
                .campaignGroupId(campaignInformation.getCampaignGroup() !=null ?
                        campaignInformation.getCampaignGroup().getId():null)
                .campaignTitle(campaignInformation.getCampaignTitle())
                .campaignTypeName(campaignInformation.getCampaignType().getDescription())
                .tags(campaignInformation.getTags())
                .campaignStartDate(campaignInformation.getCampaignStartDate())
                .campaignEndDate(campaignInformation.getCampaignEndDate())
                .versionStartDate(campaignInformation.getVersionStartDate())
                .createDate(campaignInformation.getCreateDate())
                .campaignOwner(campaignInformation.getCampaignOwner())
                .campaignTypeId(campaignInformation.getCampaignType().getId())
                .shortDescription(campaignInformation.getShortDescription())
                .hasCustomerLimit(campaignInformation.getHasCustomerLimit())
                .customerLimitSize(campaignInformation.getCustomerLimitSize())
                .build();
    }

    public static List<CampaignInformationResponse> fromListOfModel(List<CampaignInformation> campaigns) {
        return campaigns.stream().map(CampaignInformationResponse::fromModel).collect(Collectors.toList());
    }

    public static PageContent<CampaignInformationResponse> fromListOfModel(PageContent<CampaignInformation> campaignInformationPageContent) {
        return PageContent.<CampaignInformationResponse>builder()
                .content(campaignInformationPageContent.getContent().stream().map(CampaignInformationResponse::fromModel)
                        .collect(Collectors.toList()))
                .page(campaignInformationPageContent.getPage())
                .size(campaignInformationPageContent.getSize())
                .totalItems(campaignInformationPageContent.getTotalItems())
                .totalPages(campaignInformationPageContent.getTotalPages())
                .build();
    }

}
