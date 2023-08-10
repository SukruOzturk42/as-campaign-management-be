package com.anadolusigorta.campaignmanagement.infrastructure.rule.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.repository.ReferenceTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity.RuleEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleDetailResponse {

    private Long id;

    private String type;

    private Long attributeId;

    private Long campaignTypeId;

    private String operator;

    private List<String> value;

}
