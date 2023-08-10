package com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.port;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardCompanyInformation;

import java.util.List;

public interface PolicySaleRewardCompanyInformationRepository {

    List<RewardCompanyInformation> findAll();

    RewardCompanyInformation save(RewardCompanyInformation rewardCompanyInformation);

}
