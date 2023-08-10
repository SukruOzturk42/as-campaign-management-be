package com.anadolusigorta.campaignmanagement.infrastructure.reward.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftSendMethodType;
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
public class RewardGiftSendMethodTypeResponse {

    private Long id;

    private String name;

    private String description;

    public static RewardGiftSendMethodTypeResponse fromModel(RewardGiftSendMethodType rewardGiftSendMethodType) {
        return RewardGiftSendMethodTypeResponse.builder()
                .id(rewardGiftSendMethodType.getId())
                .name(rewardGiftSendMethodType.getName())
                .description(rewardGiftSendMethodType.getDescription())
                .build();
    }

    public static List<RewardGiftSendMethodTypeResponse> fromListOfModel(List<RewardGiftSendMethodType> rewardGiftSendMethodTypes) {
        return rewardGiftSendMethodTypes.stream().map(RewardGiftSendMethodTypeResponse::fromModel).collect(Collectors.toList());
    }
}
