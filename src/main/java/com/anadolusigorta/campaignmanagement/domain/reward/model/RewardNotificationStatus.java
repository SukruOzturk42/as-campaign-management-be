package com.anadolusigorta.campaignmanagement.domain.reward.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RewardNotificationStatus implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String code;

}
