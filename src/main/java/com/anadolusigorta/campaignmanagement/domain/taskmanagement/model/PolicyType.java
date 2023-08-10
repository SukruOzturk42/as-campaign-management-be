package com.anadolusigorta.campaignmanagement.domain.taskmanagement.model;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyType {

    private Long id;

    private String name;

    private Long policyGroupId;

}
