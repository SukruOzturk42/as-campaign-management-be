package com.anadolusigorta.campaignmanagement.infrastructure.reward.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftDeliveryType;
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
public class RewardGiftDeliveryTypeResponse {

    private Long id;

    private String name;

    private String description;

    public static RewardGiftDeliveryTypeResponse fromModel(RewardGiftDeliveryType rewardGiftDeliveryType) {
        return RewardGiftDeliveryTypeResponse.builder()
                .id(rewardGiftDeliveryType.getId())
                .name(rewardGiftDeliveryType.getName())
                .description(rewardGiftDeliveryType.getDescription())
                .build();
    }

    public static List<RewardGiftDeliveryTypeResponse> fromListOfModel(List<RewardGiftDeliveryType> rewardGiftDeliveryTypes) {
        return rewardGiftDeliveryTypes.stream().map(RewardGiftDeliveryTypeResponse::fromModel).collect(Collectors.toList());
    }
}
