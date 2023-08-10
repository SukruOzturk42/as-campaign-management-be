package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.port.PolicySaleRewardCompanyInformationRepository;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardCompanyInformation;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity.PolicySaleRewardCompanyInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.repository.PolicySaleRewardCompanyInformationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PolicySaleRewardCompanyInformationRepositoryJpaAdapter implements PolicySaleRewardCompanyInformationRepository {

    private final PolicySaleRewardCompanyInformationJpaRepository policySaleRewardCompanyInformationJpaRepository;

    @Override
    public List<RewardCompanyInformation> findAll() {
        return policySaleRewardCompanyInformationJpaRepository.findAll()
                .stream()
                .map(PolicySaleRewardCompanyInformationEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public RewardCompanyInformation save(RewardCompanyInformation rewardCompanyInformation) {
        var rewardCompany = policySaleRewardCompanyInformationJpaRepository.findByName(rewardCompanyInformation.getName()).orElse(null);
        if(rewardCompany == null)
            return policySaleRewardCompanyInformationJpaRepository.save(PolicySaleRewardCompanyInformationEntity.builder()
                    .name(rewardCompanyInformation.getName())
                    .description(rewardCompanyInformation.getName())
                    .build()).toModel();
        else
            throw new CampaignManagementException("reward.company.information.already.defined");

    }
}
