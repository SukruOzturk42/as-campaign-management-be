package com.anadolusigorta.campaignmanagement.infrastructure.referencetype.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AffinityCompany;
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
public class AffinityCompanyResponse {

    private Long id;

    private String name;

    private String description;

    public static AffinityCompanyResponse fromModel(AffinityCompany affinityCompany) {
        return AffinityCompanyResponse.builder()
                .id(affinityCompany.getId())
                .name(affinityCompany.getName())
                .description(affinityCompany.getDescription())
                .build();
    }

    public static List<AffinityCompanyResponse> fromListOfModel(List<AffinityCompany> affinityCompanies) {
        return affinityCompanies.stream().map(AffinityCompanyResponse::fromModel).collect(Collectors.toList());
    }
}
