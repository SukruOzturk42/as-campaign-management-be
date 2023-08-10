package com.anadolusigorta.campaignmanagement.infrastructure.campaignflow.checkcodestatus.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaignflow.checkcodestatus.entity.CheckCodeStatusFlowGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckCodeStatusFlowGroupJpaRepository extends JpaRepository<CheckCodeStatusFlowGroupEntity,Long> {

    List<CheckCodeStatusFlowGroupEntity> findAllByCampaignTypeAndRequestTypeOrderByRunOrder(String campaignType,String requestType);
}
