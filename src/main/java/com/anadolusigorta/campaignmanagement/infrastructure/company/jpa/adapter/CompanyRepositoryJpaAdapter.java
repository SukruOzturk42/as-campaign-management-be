/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter */

package com.anadolusigorta.campaignmanagement.infrastructure.company.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.company.model.Company;
import com.anadolusigorta.campaignmanagement.domain.company.port.CompanyRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.company.jpa.entity.CompanyEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.company.jpa.repository.CompanyJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyRepositoryJpaAdapter implements CompanyRepository {

	private final CompanyJpaRepository companyJpaRepository;

	@Override
	public List<Company> getCompanies() {
		return companyJpaRepository.findAll().stream()
				.map(CompanyEntity::toModel)
				.collect(Collectors.toList());
	}
}
