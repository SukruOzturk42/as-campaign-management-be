package com.anadolusigorta.campaignmanagement.infrastructure.reward.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardCompanyInformation;
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
public class RewardCompanyInformationResponse {

    private Long id;

    private String name;

    private String description;

    public static RewardCompanyInformationResponse fromModel(RewardCompanyInformation rewardCompanyInformation) {
        return RewardCompanyInformationResponse.builder()
                .id(rewardCompanyInformation.getId())
                .name(rewardCompanyInformation.getName())
                .description(rewardCompanyInformation.getDescription())
                .build();
    }

    public static List<RewardCompanyInformationResponse> fromListOfModel(List<RewardCompanyInformation> rewardGiftTypes) {
        return rewardGiftTypes.stream().map(RewardCompanyInformationResponse::fromModel).collect(Collectors.toList());
    }
}
