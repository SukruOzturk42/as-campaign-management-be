package com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.entity.AttributeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeTypeJpaRepository extends JpaRepository<AttributeTypeEntity, Long> {

}
