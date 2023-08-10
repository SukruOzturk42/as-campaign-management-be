package com.anadolusigorta.campaignmanagement.infrastructure.businessmessage.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.businessmessage.jpa.entity.BusinessMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessMessageJpaRepository extends JpaRepository<BusinessMessageEntity, Long> {

Optional<BusinessMessageEntity> findByKey(String key);

}
