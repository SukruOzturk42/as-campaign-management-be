package com.anadolusigorta.campaignmanagement.infrastructure.reward.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftTicketType;
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
public class RewardTicketTypeResponse {

    private Long id;

    private String name;

    private String description;

    public static RewardTicketTypeResponse fromModel(RewardGiftTicketType rewardGiftTicketType) {
        return RewardTicketTypeResponse.builder()
                .id(rewardGiftTicketType.getId())
                .name(rewardGiftTicketType.getName())
                .description(rewardGiftTicketType.getDescription())
                .build();
    }

    public static List<RewardTicketTypeResponse> fromListOfModel(List<RewardGiftTicketType> rewardGiftTypes) {
        return rewardGiftTypes.stream().map(RewardTicketTypeResponse::fromModel).collect(Collectors.toList());
    }
}
