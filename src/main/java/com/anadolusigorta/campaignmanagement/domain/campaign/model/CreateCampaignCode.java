package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CampaignCodeRewardType;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeKind;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeSendType;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCampaignCode {

    private Long id;

    private CampaignRuleGroup campaignRuleGroup;

    private CampaignCodeRewardType campaignCodeRewardType;

    private CodeType codeType;

    private CodeKind codeKind;

    private CodeSendType codeSendType;

    private Boolean isPairedWithCustomers;

    private Long codeCharacterLength;

    private LocalDateTime codeExpirationDate;

    private Long codeGenerationCount;

    private Long codeGeneratedCount;

    private Long codeUploadedCount;
}
