package com.anadolusigorta.campaignmanagement.infrastructure.reward.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftProduct;
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
public class RewardGiftProductResponse {

    private Long id;

    private String name;

    private String description;

    public static RewardGiftProductResponse fromModel(RewardGiftProduct rewardGiftProduct) {
        return RewardGiftProductResponse.builder()
                .id(rewardGiftProduct.getId())
                .name(rewardGiftProduct.getName())
                .description(rewardGiftProduct.getDescription())
                .build();
    }

    public static List<RewardGiftProductResponse> fromListOfModel(List<RewardGiftProduct> rewardGiftTypes) {
        return rewardGiftTypes.stream().map(RewardGiftProductResponse::fromModel).collect(Collectors.toList());
    }
}
