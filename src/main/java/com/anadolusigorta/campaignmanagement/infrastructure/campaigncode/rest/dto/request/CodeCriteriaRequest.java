package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeCriteria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeCriteriaRequest {

    private String code;

    private String contactNumber;

    public CodeCriteria toModel(){
        return CodeCriteria.builder()
                .code(code)
                .contactNumber(contactNumber)
                .build();
    }

}
