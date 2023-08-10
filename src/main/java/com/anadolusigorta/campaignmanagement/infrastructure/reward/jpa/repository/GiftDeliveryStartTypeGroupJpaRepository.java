package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.GiftDeliveryStartTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.GiftDeliveryStartTypeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface GiftDeliveryStartTypeGroupJpaRepository extends JpaRepository<GiftDeliveryStartTypeGroup,Long> {

    Set<GiftDeliveryStartTypeGroup> findAllByCampaignTypeId(Long campaignTypeId);
}
