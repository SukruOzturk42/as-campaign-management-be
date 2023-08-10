package com.anadolusigorta.campaignmanagement.infrastructure.reward.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardRole;
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
public class RewardRoleResponse {

    private Long id;

    private String name;

    private String description;

    public static RewardRoleResponse fromModel(RewardRole rewardDiscountRole) {
        return RewardRoleResponse.builder()
                .id(rewardDiscountRole.getId())
                .name(rewardDiscountRole.getName())
                .description(rewardDiscountRole.getDescription())
                .build();
    }

    public static List<RewardRoleResponse> fromListOfModel(List<RewardRole> rewardDiscountRoles) {
        return rewardDiscountRoles.stream().map(RewardRoleResponse::fromModel).collect(Collectors.toList());
    }
}
