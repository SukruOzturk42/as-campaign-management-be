package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleAttribute;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleGroup;
import com.anadolusigorta.campaignmanagement.domain.operator.ConjunctionOperatorEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaign_rule_group_is_bank")
public class CampaignRuleGroupIsBankEntity extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private ConjunctionOperatorEnum conjunctionOperator;

    @OneToMany(mappedBy = "isBank",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<CampaignRuleEntity> campaignRules;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "rule_group_id")
    private CampaignRuleGroupEntity ruleGroup;



    public CampaignRuleGroup toModel(){
        return CampaignRuleGroup.builder()
                .ruleGroupId(super.getId())
                .name(name)
                .rules(campaignRules!=null?campaignRules.stream()
                        .map(CampaignRuleEntity::toModel)
                        .sorted(Comparator.comparingLong(CampaignRuleAttribute::getRuleAttributeId))
                        .collect(Collectors.toList()):null)
                .conjunctionOperator(conjunctionOperator)
                .build();
    }
}
