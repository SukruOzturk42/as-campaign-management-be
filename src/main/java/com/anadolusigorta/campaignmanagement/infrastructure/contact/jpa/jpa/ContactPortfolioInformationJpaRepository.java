package com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.jpa;


import com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.entity.ContactPortfolioInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactPortfolioInformationJpaRepository extends JpaRepository<ContactPortfolioInformationEntity,Long> {
    Optional<ContactPortfolioInformationEntity> findByContactNumber(String contactNumber);
}
