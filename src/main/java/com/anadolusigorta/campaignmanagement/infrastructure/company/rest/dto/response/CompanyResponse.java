package com.anadolusigorta.campaignmanagement.infrastructure.company.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.company.model.Company;
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
public final class CompanyResponse {

    private Long id;

    private String name;

    private String description;


    public static CompanyResponse fromModel(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .build();
    }

    public static List<CompanyResponse> fromListOfModel(List<Company> companies) {
        return companies.stream().map(CompanyResponse::fromModel).collect(Collectors.toList());
    }

}
