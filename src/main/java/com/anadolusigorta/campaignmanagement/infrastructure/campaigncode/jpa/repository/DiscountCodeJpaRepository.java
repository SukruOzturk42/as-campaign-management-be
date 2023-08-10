package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeStatusEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.DiscountCodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface DiscountCodeJpaRepository extends JpaRepository<DiscountCodeEntity, Long>, JpaSpecificationExecutor<DiscountCodeEntity> {

    Optional<DiscountCodeEntity> findByDiscountCodeInformationIdAndCode(Long discountCodeInformationId, String campaignCode);

    List<DiscountCodeEntity> findByDiscountCodeInformationId(Long discountCodeInformationId);

    List<DiscountCodeEntity> findByDiscountCodeInformationIdAndIsActiveTrueAndContactNumberIsNull(Long discountCodeInformationId);

    Optional<DiscountCodeEntity> findTopByDiscountCodeInformationIdAndIsActiveTrueAndContactNumberIsNull(Long discountCodeInformationId);

    Optional<DiscountCodeEntity> findByDiscountCodeInformationIdAndCodeAndContactNumberAndIsActiveTrue(Long discountCodeInformationId, String campaignCode, String contactNumber);

    Optional<DiscountCodeEntity> findByCodeAndIsActiveTrue(String code);

    Long countByDiscountCodeInformationId(Long discountCodeInformationId);

    Long countByDiscountCodeInformationIdAndCodeStatusEnum(Long discountCodeInformationId, CodeStatusEnum codeStatusEnum);

    Page<DiscountCodeEntity> findAllByCodeContainsAndContactNumberContains(String code, String contactNumber, Pageable pageable);


    Page<DiscountCodeEntity> findAllByContactNumberContains(String contactNumber, Pageable pageable);

    Page<DiscountCodeEntity> findAllByCodeContains(String code, Pageable pageable);
}
