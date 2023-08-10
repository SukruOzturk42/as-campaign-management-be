package com.anadolusigorta.campaignmanagement.domain.taskmanagement.model;

import lombok.*;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyGroup {

    private Long id;

    private String name;

    private List<PolicyType> policyGroupTypes;

    private Boolean priority;

}
