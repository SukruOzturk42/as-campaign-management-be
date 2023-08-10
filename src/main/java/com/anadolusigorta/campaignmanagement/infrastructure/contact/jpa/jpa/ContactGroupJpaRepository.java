package com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.jpa;


import com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.entity.ContactEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.entity.ContactGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContactGroupJpaRepository extends JpaRepository<ContactGroupEntity, Long>, JpaSpecificationExecutor<ContactGroupEntity> {
}
