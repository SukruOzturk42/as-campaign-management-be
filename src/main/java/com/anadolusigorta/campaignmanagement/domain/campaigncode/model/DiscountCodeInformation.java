package com.anadolusigorta.campaignmanagement.domain.campaigncode.model;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.DiscountCodeInformationDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCodeInformation implements Serializable {

    private Long id;

    private String codeGroupName;

    private CodeType codeType;

    private CodeKind codeKind;

    private Boolean isPairedWithCustomers;

    private Long codeCharacterLength;

    private LocalDateTime codeExpirationDate;

    private Long codeGenerationCount;

    private Long codeGeneratedCount;

    private Long codeUploadedCount;

    private String uploadFileStatus;

    private Boolean isUsed;

    private List<DiscountCodeInformationDetail> discountCodeInformationDetails;

    private LocalDateTime uploadStartTime;

    private LocalDateTime uploadFinishTime;



}
