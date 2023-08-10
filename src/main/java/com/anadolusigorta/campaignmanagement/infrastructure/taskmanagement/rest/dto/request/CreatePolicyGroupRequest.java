package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.CreatePolicyGroup;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.PolicyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePolicyGroupRequest {

    private Long id;

    @NotNull
    private String name;

    private List<String> policyGroupTypes;

    private Boolean priority;

    public CreatePolicyGroup toModel(){
        return CreatePolicyGroup.builder()
                .id(id)
                .name(name)
                .policyGroupTypes(policyGroupTypes!=null? policyGroupTypes.stream().map(item ->
                        CreatePolicyGroup.CreatePolicyType.builder()
                                .name(item)
                                .build()).collect(Collectors.toList()) : null)
                .priority(priority != null ? priority : false)
                .build();
    }
}
