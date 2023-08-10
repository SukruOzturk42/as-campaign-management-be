package com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity.CombinedRuleRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CombinedRuleRelationJpaRepository extends JpaRepository<CombinedRuleRelationEntity, Long> {

    List<CombinedRuleRelationEntity> findAllByParentRuleId(Long parentRuleId);

}
