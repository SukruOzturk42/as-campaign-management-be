package com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleGroup;
import com.anadolusigorta.campaignmanagement.domain.operator.ConjunctionOperatorEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "combined_rule_relation")
public class CombinedRuleRelationEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "parent_id")
    RuleEntity parentRule;

    @ManyToOne
    @JoinColumn(name = "child_id")
    RuleEntity childRule;

    public CampaignRuleGroup toModel(){
        return CampaignRuleGroup.builder()
                .conjunctionOperator(childRule.getConjunctionOperator())
                .name(childRule.getName())
                .rules(childRule.getRuleAttributes().stream()
                        .map(RuleCampaignAttributeEntity::toModel)
                        .collect(Collectors.toList()))
                .build();
    }

}
