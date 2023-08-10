package com.anadolusigorta.campaignmanagement.domain.company.facade;

import com.anadolusigorta.campaignmanagement.domain.company.model.Company;
import com.anadolusigorta.campaignmanagement.domain.company.port.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyFacade {

    private final CompanyRepository companyRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Company> getCompanies() {
        return companyRepository.getCompanies();
    }


}
