package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AffinityParent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AffinityParentResponse {

    private Long id;

    private String name;

    private String description;

    public static AffinityParentResponse fromModel(AffinityParent affinityParent) {
        return AffinityParentResponse.builder()
                .id(affinityParent.getId())
                .name(affinityParent.getName())
                .description(affinityParent.getDescription())
                .build();
    }

    public static List<AffinityParentResponse> fromListOfModel(List<AffinityParent> affinityParents) {
        return affinityParents.stream().map(AffinityParentResponse::fromModel).collect(Collectors.toList());
    }
}
