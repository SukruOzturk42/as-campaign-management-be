package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardCompanyInformation;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "policy_sale_reward_company_information")
public class PolicySaleRewardCompanyInformationEntity extends AbstractEntity {

    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;

    @Column(name = "description", nullable = false, length = 400)
    private String description;

    public RewardCompanyInformation toModel(){
        return RewardCompanyInformation.builder()
                .id(super.getId())
                .name(name)
                .description(description)
                .build();
    }
}
