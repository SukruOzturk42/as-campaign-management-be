package com.anadolusigorta.campaignmanagement.infrastructure.login.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anadolusigorta.campaignmanagement.infrastructure.login.jpa.entity.SingleSignOn;

public interface SingleSignOnJpaRepository extends JpaRepository<SingleSignOn, Long> {

	Optional<SingleSignOn> findByHashValueAndValidTrue(String hashValue);
}
