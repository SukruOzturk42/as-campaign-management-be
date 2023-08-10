package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import com.anadolusigorta.campaignmanagement.domain.file.model.CampaignFile;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AttributeReferenceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDetail {

    private Long id;

    private AttributeReferenceType referenceType;

    private Long referenceTypeId;

    private String campaignLink;

    private LocalDateTime createdDate;

    private CampaignFile file;

    private String filePid;

    private String campaignText;

    private Long campaignId;

}
