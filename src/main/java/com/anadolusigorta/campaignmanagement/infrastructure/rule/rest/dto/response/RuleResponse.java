package com.anadolusigorta.campaignmanagement.infrastructure.rule.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignType;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleOperationsRequests;
import com.anadolusigorta.campaignmanagement.infrastructure.recordtracking.rest.dto.SaleProcessResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity.RuleEntity;
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
public class RuleResponse {

    private Long id;

    private String name;

    private String campaignTypeName;

    private Long campaignTypeId;

    private LocalDateTime createdAt;


    public static RuleResponse fromModel(RuleEntity ruleEntity) {
        return RuleResponse.builder()
                .id(ruleEntity.getId())
                .name(ruleEntity.getName())
                .campaignTypeName(ruleEntity.getCampaignType().getDescription())
                .campaignTypeId(ruleEntity.getCampaignType().getId())
                .createdAt(ruleEntity.getCreatedAt())
                .build();
    }

    public static List<RuleResponse> fromListOfModel(List<RuleEntity> ruleEntities) {
        return ruleEntities.stream().map(RuleResponse::fromModel).collect(Collectors.toList());
    }

    public static PageContent<RuleResponse> fromPageOfModel(PageContent<RuleEntity> findSaleCampaignsRequests) {
        return PageContent.<RuleResponse>builder()
                .content(findSaleCampaignsRequests.getContent().stream().map(RuleResponse::fromModel)
                        .collect(Collectors.toList()))
                .page(findSaleCampaignsRequests.getPage())
                .size(findSaleCampaignsRequests.getSize())
                .totalItems(findSaleCampaignsRequests.getTotalItems())
                .build();
    }
}
