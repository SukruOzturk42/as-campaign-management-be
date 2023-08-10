package com.anadolusigorta.campaignmanagement.domain.company.facade;

import com.anadolusigorta.campaignmanagement.domain.company.model.CompanyCampaignStructure;
import com.anadolusigorta.campaignmanagement.domain.company.port.CompanyCampaignStructureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyCampaignStructureFacade {

    private final CompanyCampaignStructureRepository companyCampaignStructureRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CompanyCampaignStructure> getCompanyCampaignStructures() {
        return companyCampaignStructureRepository.getCompanyCampaignStructures();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public CompanyCampaignStructure getCompanyCampaignStructureByCompanyId(Long companyId) {
        return companyCampaignStructureRepository.getCompanyCampaignStructureByCompanyId(companyId);
    }
}
