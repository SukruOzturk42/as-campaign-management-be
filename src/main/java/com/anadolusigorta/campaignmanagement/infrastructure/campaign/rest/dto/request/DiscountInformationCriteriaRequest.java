package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;


import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCodeInformationCriteria;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.CodeTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountInformationCriteriaRequest {

    Long id;

    String codeGroupName;

    Long codeTypeId;

    public DiscountCodeInformationCriteria toModel(){
        return DiscountCodeInformationCriteria.builder()
                .id(id)
                .codeGroupName(codeGroupName)
                .codeTypeId(codeTypeId)
                .build();
    }
}
