package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.DiscountCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDiscountCodeInformation {

    private Long id;

    private String codeGroupName;

    private Long codeTypeId;

    private String code;

    private Long codeKindId;

    private boolean isPairedWithCustomers;

    private Long codeCharacterLength;

    private LocalDateTime codeExpirationDate;

    private Long codeGenerationCount;


    private List<DiscountCode> discountCodes;

}
