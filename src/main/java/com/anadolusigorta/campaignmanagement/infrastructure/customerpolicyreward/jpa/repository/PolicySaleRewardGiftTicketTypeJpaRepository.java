package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity.PolicySaleRewardCompanyInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity.PolicySaleRewardGiftTicketTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PolicySaleRewardGiftTicketTypeJpaRepository extends JpaRepository<PolicySaleRewardGiftTicketTypeEntity,Long> {

    Optional<PolicySaleRewardGiftTicketTypeEntity> findByName(String name);

}
