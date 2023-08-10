package com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.jpa;


import com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.entity.HealthInsuredProductInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HealthInsuredProductInformationJpaRepository extends JpaRepository<HealthInsuredProductInformationEntity,Long> {

    List<HealthInsuredProductInformationEntity> findByContactNumberAndPolicyCodeIsNotNull(String contactNumber);

}
