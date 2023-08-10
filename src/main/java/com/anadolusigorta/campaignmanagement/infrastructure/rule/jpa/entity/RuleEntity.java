package com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRule;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleAttribute;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleGroup;
import com.anadolusigorta.campaignmanagement.domain.operator.ConjunctionOperatorEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rule")
public class RuleEntity extends AbstractEntity {

    @Size(max = 64, message = "Rule name is too long")
    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private ConjunctionOperatorEnum conjunctionOperator;

    @OneToMany(mappedBy = "rule",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<RuleCampaignAttributeEntity> ruleAttributes;

    @OneToMany(mappedBy = "parentRule")
    private Set<CombinedRuleRelationEntity> parents;


    @OneToMany(mappedBy = "childRule", fetch = FetchType.EAGER)
    private Set<CombinedRuleRelationEntity> children = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "campaign_type_id")
    private CampaignTypeEntity campaignType;

    public CampaignRuleGroup toModel(){
        return CampaignRuleGroup.builder()
                .conjunctionOperator(conjunctionOperator)
                .name(name)
                .children(!children.isEmpty()?children.stream()
                        .map(CombinedRuleRelationEntity::toModel).collect(Collectors.toList())
                        : null)
                .rules(ruleAttributes.stream()
                        .map(RuleCampaignAttributeEntity::toModel)
                        .sorted(Comparator.comparingLong(CampaignRuleAttribute::getOrder))
                        .collect(Collectors.toList()))
                .campaignType(campaignType != null ? campaignType.toModel() : null)
                .build();
    }

}
