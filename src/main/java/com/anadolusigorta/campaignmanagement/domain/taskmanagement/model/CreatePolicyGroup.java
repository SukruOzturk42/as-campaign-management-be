package com.anadolusigorta.campaignmanagement.domain.taskmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePolicyGroup {

    private Long id;

    private String name;

    private List<CreatePolicyType> policyGroupTypes;

    private Boolean priority;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreatePolicyType {

        private Long id;

        private String name;

        private Long policyGroupId;

    }

}
