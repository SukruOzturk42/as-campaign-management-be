package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.sale.model.RemoveSaleReward;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class RemoveSaleRewardRequest {



    @NotNull
    private String contactNumber;


    @NotNull
    private String policyNumber;

    @NotNull
    private Long campaignId;

    @NotNull
    private Long ruleGroupId;

    @NotNull
    private String policyEndorsementNumber;


    public RemoveSaleReward toModel() {
        log.info(String.format("Remove sale reward request :%s",this));
        return RemoveSaleReward.builder()
                .contactNumber(contactNumber)
                .policyNumber(policyNumber)
                .campaignId(campaignId)
                .ruleGroupId(ruleGroupId)
                .policyEndorsementNumber(policyEndorsementNumber)
                .build();
    }


}
