package com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.jpa;

import com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

import java.util.List;

public interface ContactJpaRepository extends JpaRepository<ContactEntity, Long> {

	List<ContactEntity>  findByContactGroupId(Long contactGroupId);
	Optional<ContactEntity> findByContactGroupIdAndContactNo(Long contactGroupId,String contactNo);
}
