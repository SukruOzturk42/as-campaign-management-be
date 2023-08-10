package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.PolicyGroup;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.PolicyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyGroupResponse {

    private Long id;

    private String name;

    private String policyGroupTypes;

    private Boolean priority;

    public static PolicyGroupResponse fromModel(PolicyGroup policyGroup){
        return PolicyGroupResponse.builder()
                .id(policyGroup.getId())
                .name(policyGroup.getName())
                .policyGroupTypes(policyGroup.getPolicyGroupTypes()!=null?policyGroup.getPolicyGroupTypes().stream()
                        .map(PolicyType::getName).collect(Collectors.joining(",")): null)
                .priority(policyGroup.getPriority())
                .build();
    }

    public static PageContent<PolicyGroupResponse> fromListOfModel(PageContent<PolicyGroup> policyGroup) {
        return PageContent.<PolicyGroupResponse>builder()
                .content(policyGroup.getContent().stream().map(PolicyGroupResponse::fromModel)
                        .collect(Collectors.toList()))
                .page(policyGroup.getPage())
                .size(policyGroup.getSize())
                .totalItems(policyGroup.getTotalItems())
                .build();
    }
}
