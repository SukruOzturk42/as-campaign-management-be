package com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.entity.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttributeJpaRepository extends JpaRepository<AttributeEntity, Long> {

    List<AttributeEntity> findAllByServiceNameNotNull();

    Optional<AttributeEntity> findByNameAndCampaignTypeId(String name, Long campaignTypeId);

    List<AttributeEntity> findByCampaignTypeId(Long campaignTypeId);

    List<AttributeEntity> findByCampaignTypeIdAndIsActiveTrue(Long campaignTypeId);

    List<AttributeEntity> findByName(String name);

}
