package com.anadolusigorta.campaignmanagement.infrastructure.company.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.company.model.CompanyCampaignStructure;
import com.anadolusigorta.campaignmanagement.domain.company.port.CompanyCampaignStructureRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.company.jpa.entity.CompanyCampaignStructureEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.company.jpa.repository.CompanyCampaignStructureJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyCampaignStructureRepositoryJpaAdapter implements CompanyCampaignStructureRepository {
    private final CompanyCampaignStructureJpaRepository companyCampaignStructureJpaRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CompanyCampaignStructure> getCompanyCampaignStructures() {
        return companyCampaignStructureJpaRepository.findAll().stream()
                .map(CompanyCampaignStructureEntity::toModel).collect(Collectors.toList());
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public CompanyCampaignStructure getCompanyCampaignStructureByCompanyId(Long companyId) {
        return companyCampaignStructureJpaRepository.findByCompanyIdAndParentIsNull(companyId).toModel();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public CompanyCampaignStructureEntity getById(Long id) {
        return companyCampaignStructureJpaRepository
                .findById(id)
                .orElseThrow(()->new CampaignManagementException("structure.not.found"));
    }
}
