package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity.PolicySaleRewardCampaignEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity.PolicySaleRewardCompanyInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PolicySaleRewardCompanyInformationJpaRepository
        extends JpaRepository<PolicySaleRewardCompanyInformationEntity,Long> {

    Optional<PolicySaleRewardCompanyInformationEntity> findByName(String name);

}
