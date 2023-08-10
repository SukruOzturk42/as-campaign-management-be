package com.anadolusigorta.campaignmanagement.infrastructure.company.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.company.jpa.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, Long> {

}
