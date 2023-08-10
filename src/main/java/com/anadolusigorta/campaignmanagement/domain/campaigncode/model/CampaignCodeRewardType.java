package com.anadolusigorta.campaignmanagement.domain.campaigncode.model;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardDiscountKind;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignCodeRewardType implements Serializable {

    private RewardDiscountKind rewardDiscountKind;

    private Boolean hasCodeCollection;

}
