package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SoldPolicyDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SoldPolicyDetailJpaRepository extends JpaRepository<SoldPolicyDetailEntity, Long> {

    List<SoldPolicyDetailEntity> findByProposalNumberAndRevisionNumberAndSaleCampaignContactNumber(String proposalNumber, String revisionNumber, String contactNumber);

    List<SoldPolicyDetailEntity> findByProposalNumberAndSaleCampaignContactNumber(String proposalNumber, String contactNumber);


}
