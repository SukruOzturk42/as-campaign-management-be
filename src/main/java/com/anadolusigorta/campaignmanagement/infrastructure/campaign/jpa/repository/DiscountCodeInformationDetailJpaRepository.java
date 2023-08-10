package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.DiscountCodeInformationDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiscountCodeInformationDetailJpaRepository extends JpaRepository<DiscountCodeInformationDetailEntity, Long> {

    List<DiscountCodeInformationDetailEntity> findByDiscountCodeInformationId(Long discountCodeInformationId);

}
