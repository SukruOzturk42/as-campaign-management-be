package com.anadolusigorta.campaignmanagement.infrastructure.company.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.company.model.CompanyCampaignStructure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class CompanyCampaignStructureResponse {

    private long id;

    private String name;

    private String description;

    private String rewardName;

    private String rewardDescription;

    private Long companyId;

    private Set<CompanyCampaignStructureResponse> children;

    public static CompanyCampaignStructureResponse fromModel(CompanyCampaignStructure companyCampaignStructure) {
        return CompanyCampaignStructureResponse.builder()
                .id(companyCampaignStructure.getId())
                .name(companyCampaignStructure.getName())
                .description(companyCampaignStructure.getDescription())
                .rewardName(companyCampaignStructure.getRewardName())
                .rewardDescription(companyCampaignStructure.getRewardDescription())
                .companyId(companyCampaignStructure.getCompanyId())
                .children(companyCampaignStructure.getChildren().stream()
                        .map(CompanyCampaignStructureResponse::fromModel).collect(Collectors.toSet())).build();
    }

    public static List<CompanyCampaignStructureResponse> fromListOfModel(List<CompanyCampaignStructure> companyCampaignStructure){

        return companyCampaignStructure.stream()
                .map(CompanyCampaignStructureResponse::fromModel)
                .collect(Collectors.toList());
    }
}
