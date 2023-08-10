package com.anadolusigorta.campaignmanagement.infrastructure.reward.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftKind;
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
public class RewardGiftKindResponse {

    private Long id;

    private String name;

    private String description;

    public static RewardGiftKindResponse fromModel(RewardGiftKind rewardGiftKind) {
        return RewardGiftKindResponse.builder()
                .id(rewardGiftKind.getId())
                .name(rewardGiftKind.getName())
                .description(rewardGiftKind.getDescription())
                .build();
    }

    public static List<RewardGiftKindResponse> fromListOfModel(List<RewardGiftKind> rewardGiftKinds) {
        return rewardGiftKinds.stream().map(RewardGiftKindResponse::fromModel).collect(Collectors.toList());
    }
}
