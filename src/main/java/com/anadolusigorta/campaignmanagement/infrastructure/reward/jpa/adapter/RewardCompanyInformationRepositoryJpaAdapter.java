package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardCompanyInformation;
import com.anadolusigorta.campaignmanagement.domain.reward.port.RewardCompanyInformationRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.RewardCompanyInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.RewardCompanyInformationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RewardCompanyInformationRepositoryJpaAdapter implements RewardCompanyInformationRepository {

    private final RewardCompanyInformationJpaRepository rewardCompanyInformationJpaRepository;

    @Override
    public List<RewardCompanyInformation> findAll() {
        return rewardCompanyInformationJpaRepository.findAll()
                .stream()
                .map(RewardCompanyInformationEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public RewardCompanyInformation save(RewardCompanyInformation rewardCompanyInformation) {
         var rewardCompany = rewardCompanyInformationJpaRepository.findByName(rewardCompanyInformation.getName()).orElse(null);
         if(rewardCompany == null)
             return rewardCompanyInformationJpaRepository.save(RewardCompanyInformationEntity.builder()
             .name(rewardCompanyInformation.getName())
             .description(rewardCompanyInformation.getName())
             .build()).toModel();
         else
             throw new CampaignManagementException("reward.company.information.already.defined");

    }
}
