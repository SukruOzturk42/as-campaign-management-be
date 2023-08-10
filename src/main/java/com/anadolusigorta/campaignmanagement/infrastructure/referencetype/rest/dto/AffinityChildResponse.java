package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AffinityChild;
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
public class AffinityChildResponse {

    private Long id;

    private String name;

    private String description;

    public static AffinityChildResponse fromModel(AffinityChild affinitychild) {
        return AffinityChildResponse.builder()
                .id(affinitychild.getId())
                .name(affinitychild.getName())
                .description(affinitychild.getDescription())
                .build();
    }

    public static List<AffinityChildResponse> fromListOfModel(List<AffinityChild> affinityChildList) {
        return affinityChildList.stream().map(AffinityChildResponse::fromModel).collect(Collectors.toList());
    }
}
