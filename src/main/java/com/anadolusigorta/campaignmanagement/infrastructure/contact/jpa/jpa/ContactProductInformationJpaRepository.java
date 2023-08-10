package com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.jpa;


import com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.entity.ContactProductInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ContactProductInformationJpaRepository extends JpaRepository<ContactProductInformationEntity,Long> {

    List<ContactProductInformationEntity> findByContactNumberAndPolicyCodeIsNotNull(String contactNumber);

}
