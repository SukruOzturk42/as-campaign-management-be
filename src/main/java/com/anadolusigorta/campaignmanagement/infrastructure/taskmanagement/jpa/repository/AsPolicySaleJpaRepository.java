package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.AsPolicySaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AsPolicySaleJpaRepository extends JpaRepository<AsPolicySaleEntity, Long> {

    Optional<AsPolicySaleEntity> findByProposalNumber(String proposalNumber);

    Optional<AsPolicySaleEntity> findTopByCustomerNoAndPolicyTypeInAndPolicyOwnerAgencyNoIsNotNullOrderBySaleDateAsc(String customerNo,List<Long> policyType);


    List<AsPolicySaleEntity> findByCustomerNoAndPolicyTypeInAndPolicyOwnerAgencyNoIsNotNullOrderBySaleDateAsc(String customerNo,List<Long> policyTypes);

    Optional<AsPolicySaleEntity> findTopByCustomerNoAndPolicyTypeInAndSubjectValueAndPolicyOwnerAgencyNoIsNotNullOrderBySaleDateAsc(String customerNo, List<Long> policyType, String subjectValue);


    List<AsPolicySaleEntity> findByCustomerNoAndPolicyTypeInAndSubjectValueAndPolicyOwnerAgencyNoIsNotNullOrderBySaleDateAsc(String customerNo, List<Long> policyType, String subjectValue);


}
