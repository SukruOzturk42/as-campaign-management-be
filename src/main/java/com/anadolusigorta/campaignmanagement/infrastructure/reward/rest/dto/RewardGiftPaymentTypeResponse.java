package com.anadolusigorta.campaignmanagement.infrastructure.reward.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftPaymentType;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftType;
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
public class RewardGiftPaymentTypeResponse {

    private Long id;

    private String name;

    private String description;

    public static RewardGiftPaymentTypeResponse fromModel(RewardGiftPaymentType rewardGiftPaymentType) {
        return RewardGiftPaymentTypeResponse.builder()
                .id(rewardGiftPaymentType.getId())
                .name(rewardGiftPaymentType.getName())
                .description(rewardGiftPaymentType.getDescription())
                .build();
    }

    public static List<RewardGiftPaymentTypeResponse> fromListOfModel(List<RewardGiftPaymentType> rewardGiftTypes) {
        return rewardGiftTypes.stream().map(RewardGiftPaymentTypeResponse::fromModel).collect(Collectors.toList());
    }
}
