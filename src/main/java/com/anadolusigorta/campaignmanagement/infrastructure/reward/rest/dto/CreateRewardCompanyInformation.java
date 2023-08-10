package com.anadolusigorta.campaignmanagement.infrastructure.reward.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardCompanyInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRewardCompanyInformation {


    private String name;

    public RewardCompanyInformation toModel() {
        return RewardCompanyInformation.builder()
                .name(name)
                .build();
    }

}