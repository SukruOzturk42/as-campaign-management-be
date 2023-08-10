/* odeon_sukruo created on 30.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.operator.jpa.repository */

package com.anadolusigorta.campaignmanagement.infrastructure.operator.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.entity.AttributeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.operator.jpa.entity.AttributeOperatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttributeOperatorJpaRepository extends JpaRepository<AttributeOperatorEntity, Long> {
	Optional<AttributeOperatorEntity> findById(Long id);
	List<AttributeOperatorEntity> findByAttributeId(Long attributeId);
	Optional<AttributeOperatorEntity> findAttributeOperatorEntityByNameAndAttribute(String name, AttributeEntity attribute);
}
