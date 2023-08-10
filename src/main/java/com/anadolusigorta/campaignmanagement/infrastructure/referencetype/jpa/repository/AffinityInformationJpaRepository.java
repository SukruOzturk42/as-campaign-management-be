package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.entity.AffinityInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.entity.ReferenceTypeEntity;
import org.apache.kafka.common.quota.ClientQuotaAlteration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AffinityInformationJpaRepository extends JpaRepository<AffinityInformationEntity, Long> {

    Optional<AffinityInformationEntity> findTopByOrderByExtCodeDesc();

    AffinityInformationEntity findByExtCode(Long name);

    Optional<AffinityInformationEntity> findByAffinityCompanyIdAndAffinityParentIdAndAffinityChildIsNull(Long companyId, Long parentId);

    List<AffinityInformationEntity> findAllByAffinityCompanyIdAndAffinityParentIdAndAffinityChildIsNotNull(Long companyId, Long parentId);

    Optional<AffinityInformationEntity> findTopByAffinityCompanyIdAndAffinityParentIdAndAffinityChildId(Long affinityCompany, Long affinityParent, Long affinityChild);

    Optional<AffinityInformationEntity> findByAffinityCompany_IdAndAffinityParent_IdAndAffinityChild_Id(Long affinityCompany, Long affinityParent, Long affinityChild);

    Optional<AffinityInformationEntity> findByAffinityCompanyIdAndAffinityParentIdAndAffinityChildIdAndIsSelectableAndIsActiveAndIsIsBankCooperation(Long affinityCompany, Long affinityParent, Long affinityChild,Boolean isSelectiable, Boolean isActive, Boolean isIsBankCooperation);
}
