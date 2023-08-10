package com.anadolusigorta.campaignmanagement.domain.company.port;

import com.anadolusigorta.campaignmanagement.domain.company.model.Company;

import java.util.List;

public interface CompanyRepository {

    List<Company> getCompanies();

}
