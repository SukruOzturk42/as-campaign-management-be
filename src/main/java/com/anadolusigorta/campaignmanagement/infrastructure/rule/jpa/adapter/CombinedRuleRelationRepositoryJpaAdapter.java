package com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.adapter;

import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity.CombinedRuleRelationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity.RuleEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.repository.CombinedRuleRelationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CombinedRuleRelationRepositoryJpaAdapter {

    private final CombinedRuleRelationJpaRepository combinedRuleRelationJpaRepository;

    public CombinedRuleRelationEntity saveCombinedRuleRelation(RuleEntity parent, RuleEntity child) {

        CombinedRuleRelationEntity combinedRuleRelationEntity = new CombinedRuleRelationEntity();

        combinedRuleRelationEntity.setParentRule(parent);
        combinedRuleRelationEntity.setChildRule(child);

        return combinedRuleRelationJpaRepository.save(combinedRuleRelationEntity);
    }

}
