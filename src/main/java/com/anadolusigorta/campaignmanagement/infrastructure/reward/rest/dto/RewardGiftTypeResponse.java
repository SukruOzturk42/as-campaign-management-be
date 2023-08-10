package com.anadolusigorta.campaignmanagement.infrastructure.reward.rest.dto;

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
public class RewardGiftTypeResponse {

    private Long id;

    private String name;

    private String description;

    public static RewardGiftTypeResponse fromModel(RewardGiftType rewardGiftType) {
        return RewardGiftTypeResponse.builder()
                .id(rewardGiftType.getId())
                .name(rewardGiftType.getName())
                .description(rewardGiftType.getDescription())
                .build();
    }

    public static List<RewardGiftTypeResponse> fromListOfModel(List<RewardGiftType> rewardGiftTypes) {
        return rewardGiftTypes.stream().map(RewardGiftTypeResponse::fromModel).collect(Collectors.toList());
    }
}
