package com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity.RuleEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.FindSaleCampaignsRequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RuleJpaRepository extends JpaRepository<RuleEntity, Long>, JpaSpecificationExecutor<RuleEntity> {

    List<RuleEntity> findAllByCampaignTypeId(Long campaignTypeId);

    List<RuleEntity> findAllByOrderByCreatedAtAsc();

    Page<RuleEntity> findAll(Pageable pageable);

}
