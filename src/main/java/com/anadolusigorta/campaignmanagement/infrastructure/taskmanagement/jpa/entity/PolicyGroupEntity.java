package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.PolicyGroup;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "policy_group")
public class PolicyGroupEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "policyGroup" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<PolicyGroupTypesEntity> policyGroupTypes;

    @Column(name = "priority")
    private Boolean priority = false;

    public PolicyGroup toModel(){
        return PolicyGroup.builder()
                .id(getId())
                .name(name)
                .policyGroupTypes(policyGroupTypes != null ?
                        policyGroupTypes.stream().map(PolicyGroupTypesEntity::toModel)
                                .collect(Collectors.toList()) : null)
                .priority(priority)
                .build();
    }

}
