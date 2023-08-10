package com.anadolusigorta.campaignmanagement.domain.company.port;

import com.anadolusigorta.campaignmanagement.domain.company.model.CompanyCampaignStructure;

import java.util.List;

public interface CompanyCampaignStructureRepository {

    List<CompanyCampaignStructure> getCompanyCampaignStructures();

    CompanyCampaignStructure getCompanyCampaignStructureByCompanyId(Long companyId);
}
