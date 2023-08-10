package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeKind;
import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignGoalType;
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
public class CodeKindResponse {

    private Long id;

    private String name;

    private String description;

    public static CodeKindResponse fromModel(CodeKind codeKind) {
        return CodeKindResponse.builder()
                .id(codeKind.getId())
                .name(codeKind.getName())
                .description(codeKind.getDescription())
                .build();
    }

    public static List<CodeKindResponse> fromListOfModel(List<CodeKind> codeKinds) {
        return codeKinds.stream().map(CodeKindResponse::fromModel).collect(Collectors.toList());
    }

}