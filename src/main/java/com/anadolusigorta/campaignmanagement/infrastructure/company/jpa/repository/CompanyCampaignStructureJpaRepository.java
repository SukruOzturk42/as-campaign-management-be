package com.anadolusigorta.campaignmanagement.infrastructure.company.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.company.jpa.entity.CompanyCampaignStructureEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyCampaignStructureJpaRepository extends JpaRepository<CompanyCampaignStructureEntity, Long> {
    CompanyCampaignStructureEntity findByCompanyIdAndParentIsNull(Long companyId);

}
