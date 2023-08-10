package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CheckSaleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckCodeStatusResponse {

    private String transactionId;

    private String result;

    private Boolean success;

    private Long campaignId;

    private Long ruleGroupId;

    private String discountValue;

    private List<CheckCodeStatusResponse> insuredCheckCodeResponse;

    public static CheckCodeStatusResponse fromModel(CheckSaleStatus checkSaleStatus) {
        log.info(String.format("Check Sale Service Response Sale Status: %s", checkSaleStatus.toString()));
        return CheckCodeStatusResponse.builder()
                .transactionId(checkSaleStatus.getTransactionId())
                .success(checkSaleStatus.getSuccess())
                .campaignId(checkSaleStatus.getCampaignId())
                .ruleGroupId(checkSaleStatus.getRuleGroupId())
                .discountValue(checkSaleStatus.getDiscountValue())
                .insuredCheckCodeResponse(checkSaleStatus.getInsuredCheckStatus()!=null?
                        fromListModel(checkSaleStatus.getInsuredCheckStatus())
                        :null)
                .build();
    }

    private static List<CheckCodeStatusResponse> fromListModel(List<CheckSaleStatus> checkSaleStatusList){
        return checkSaleStatusList.stream()
                .map(CheckCodeStatusResponse::fromModel)
                .collect(Collectors.toList());
    }

}
