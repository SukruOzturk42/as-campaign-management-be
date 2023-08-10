package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.entity.ReferenceTypeEntity;
import org.apache.kafka.common.quota.ClientQuotaAlteration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReferenceTypeJpaRepository extends JpaRepository<ReferenceTypeEntity, Long> {

	List<ReferenceTypeEntity> findByAttributeId(Long attributeId);

	List<ReferenceTypeEntity> findByAttributeIdIn(List<Long> attributeId);

	List<ReferenceTypeEntity> findByAttributeNameAndAttributeCampaignTypeId(String attributeName,Long campaignTypeId);

	List<ReferenceTypeEntity> findByAttributeNameAndAttributeCampaignTypeName(String attributeName,String campaignTypeName);

	void deleteByAttributeName(String attributeName);

	ReferenceTypeEntity findByDescription(String description);

	ReferenceTypeEntity findByNameAndAttributeId(String name, Long attributeId);

	Optional<ReferenceTypeEntity> findTopByAttributeIdOrderByNameDesc(Long attributeId);

	ReferenceTypeEntity findByNameAndAttributeId(String name, String attributeId);

}
