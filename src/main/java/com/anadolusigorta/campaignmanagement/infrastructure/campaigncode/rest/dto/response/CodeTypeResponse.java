package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeType;
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
public class CodeTypeResponse {

    private Long id;

    private String name;

    private String description;

    public static CodeTypeResponse fromModel(CodeType codeType) {
        return CodeTypeResponse.builder()
                .id(codeType.getId())
                .name(codeType.getName())
                .description(codeType.getDescription())
                .build();
    }

    public static List<CodeTypeResponse> fromListOfModel(List<CodeType> codeTypes) {
        return codeTypes.stream().map(CodeTypeResponse::fromModel).collect(Collectors.toList());
    }

}
