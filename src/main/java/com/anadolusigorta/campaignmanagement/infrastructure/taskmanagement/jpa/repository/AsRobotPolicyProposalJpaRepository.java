package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.AsPolicySaleEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.AsRobotPolicyProposalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsRobotPolicyProposalJpaRepository extends JpaRepository<AsRobotPolicyProposalEntity, Long> {

    List<AsRobotPolicyProposalEntity> findByIsActiveTrue();

    List<AsRobotPolicyProposalEntity> findByIsActiveTrueAndKnimeTaskTaskTypeId(Long taskTypeId);

    List<AsRobotPolicyProposalEntity> findByIsActiveTrueAndKnimeTaskId(String knimeTaskId);




}
