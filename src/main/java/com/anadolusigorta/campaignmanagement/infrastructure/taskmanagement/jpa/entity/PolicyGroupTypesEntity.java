package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.PolicyType;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "policy_group_types")
public class PolicyGroupTypesEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "policy_group_id", nullable = false)
    private PolicyGroupEntity policyGroup;

    public PolicyType toModel(){
        return PolicyType.builder()
                .id(getId())
                .name(name)
                .policyGroupId(policyGroup != null ? policyGroup.getId() : null)
                .build();
    }

}
