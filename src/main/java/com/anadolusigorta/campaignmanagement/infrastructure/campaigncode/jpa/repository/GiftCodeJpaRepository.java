package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeStatusEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.DiscountCodeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.GiftCodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface GiftCodeJpaRepository extends JpaRepository<GiftCodeEntity, Long>, JpaSpecificationExecutor<GiftCodeEntity> {

    List<GiftCodeEntity> findAllByGiftCodeInformationId(Long giftCodeInformationId);

    List<GiftCodeEntity> findAllBySaleRewardGiftId(Long saleRewardGiftId);


    Page<GiftCodeEntity> findByGiftCodeInformationIdAndContactNumberIsNull(Long giftCodeInformationId,Pageable page);

    Optional<GiftCodeEntity> findTopByGiftCodeInformationIdAndContactNumberIsNull(Long giftCodeInformationId);

    Optional<GiftCodeEntity> findByCodeAndGiftCodeInformationIdAndContactNumberIsNull(String code,Long giftCodeInformationId);

    Optional<GiftCodeEntity> findByCodeAndGiftCodeInformationId(String code,Long giftCodeInformationId);




    Long countByGiftCodeInformationId(Long giftCodeInformationId);

    Long countByGiftCodeInformationIdAndCodeStatusEnum(Long giftCodeInformationId, CodeStatusEnum codeStatusEnum);

    Page<GiftCodeEntity> findAllByCodeContainsAndContactNumberContains(String code, String contactNumber, Pageable pageable);

    Page<GiftCodeEntity> findAllByContactNumberContains(String contactNumber, Pageable pageable);

    Page<GiftCodeEntity> findAllByCodeContains(String code, Pageable pageable);
}
