package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CustomerCampaignEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.DiscountCodeInformationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DiscountCodeInformationJpaRepository extends JpaRepository<DiscountCodeInformationEntity, Long>, JpaSpecificationExecutor<DiscountCodeInformationEntity> {

    List<DiscountCodeInformationEntity> findAllByCodeExpirationDateIsAfter(LocalDateTime now);

    Page<DiscountCodeInformationEntity> findAllByCodeGroupNameContains(String codeGroupName, Pageable pageable);

    Page<DiscountCodeInformationEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);

}
