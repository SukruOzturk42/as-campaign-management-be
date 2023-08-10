package com.anadolusigorta.campaignmanagement.infrastructure.reward.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftDeliveryStartType;
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
public class RewardDeliveryStartTypeResponse {

    private Long id;

    private String name;

    private String description;

    public static RewardDeliveryStartTypeResponse fromModel(RewardGiftDeliveryStartType rewardGiftDeliveryStartType)
    {
        return RewardDeliveryStartTypeResponse.builder()
                .id(rewardGiftDeliveryStartType.getId())
                .name(rewardGiftDeliveryStartType.getName())
                .description(rewardGiftDeliveryStartType.getDescription())
                .build();
    }

    public static List<RewardDeliveryStartTypeResponse> fromListOfModel(List<RewardGiftDeliveryStartType> rewardGiftDeliveryStartTypes)
    {
        return rewardGiftDeliveryStartTypes.stream().map(RewardDeliveryStartTypeResponse::fromModel).collect(Collectors.toList());
    }
}
