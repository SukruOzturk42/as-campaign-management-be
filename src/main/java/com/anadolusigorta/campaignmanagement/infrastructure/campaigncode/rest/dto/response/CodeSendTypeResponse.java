package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeSendType;
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
public class CodeSendTypeResponse {

    private Long id;

    private String name;

    private String description;

    public static CodeSendTypeResponse fromModel(CodeSendType codeSendType) {
        return CodeSendTypeResponse.builder()
                .id(codeSendType.getId())
                .name(codeSendType.getName())
                .description(codeSendType.getDescription())
                .build();
    }

    public static List<CodeSendTypeResponse> fromListOfModel(List<CodeSendType> codeSendTypes) {
        return codeSendTypes.stream().map(CodeSendTypeResponse::fromModel).collect(Collectors.toList());
    }

}
