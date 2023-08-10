package com.anadolusigorta.campaignmanagement.infrastructure.sale.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleCampaignInformation;
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
public class SaleCampaignInformationResponse {

    private Long campaignId;
    private Long version;
    private String campaignName;
    private LocalDateTime campaignStartDate;
    private LocalDateTime campaignEndDate;
    private LocalDateTime createDate;
    private long totalSaleCount;
    private long totalProposalCount;
    private Boolean isTriggeredRewardSend;
    private Boolean isStartedRewardSend;
    private String campaignType;


    public static SaleCampaignInformationResponse fromModel(SaleCampaignInformation saleCampaignInformation) {
        return SaleCampaignInformationResponse.builder()
                .campaignId(saleCampaignInformation.getCampaignInformation().getCampaignId())
                .version(saleCampaignInformation.getCampaignInformation().getVersion())
                .campaignName(saleCampaignInformation.getCampaignInformation().getCampaignName())
                .campaignStartDate(saleCampaignInformation.getCampaignInformation().getCampaignStartDate())
                .campaignEndDate(saleCampaignInformation.getCampaignInformation().getCampaignEndDate())
                .createDate(saleCampaignInformation.getCampaignInformation().getCreateDate())
                .totalSaleCount(saleCampaignInformation.getTotalSaleCount())
                .totalProposalCount(saleCampaignInformation.getTotalProposalCount())
                .isStartedRewardSend(saleCampaignInformation.getCampaignInformation().getIsStartedRewardSend())
                .isTriggeredRewardSend(saleCampaignInformation.getCampaignInformation().getIsTriggeredRewardSend())
                .campaignType(saleCampaignInformation.getCampaignInformation().getCampaignType().getDescription())
                .build();
    }

    public static List<SaleCampaignInformationResponse> fromListOfModel(List<SaleCampaignInformation> saleCampaignInformation) {
        return saleCampaignInformation.stream().map(SaleCampaignInformationResponse::fromModel).collect(Collectors.toList());

    }

    public static PageContent<SaleCampaignInformationResponse> fromListOfModel(PageContent<SaleCampaignInformation> saleCampaignInformation) {

        return PageContent.<SaleCampaignInformationResponse>builder()
                .content(fromListOfModel(saleCampaignInformation.getContent()))
                .page(saleCampaignInformation.getPage())
                .size(saleCampaignInformation.getSize())
                .totalItems(saleCampaignInformation.getTotalItems())
                .totalPages(saleCampaignInformation.getTotalPages())
                .build();

    }
}
