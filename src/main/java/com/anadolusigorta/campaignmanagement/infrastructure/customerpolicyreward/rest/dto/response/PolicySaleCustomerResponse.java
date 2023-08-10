package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleCustomer;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCode;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCodeSendStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleRewardCampaign;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicySaleCustomerResponse {

    private Long id;
    private String customerNo;
    private String policySaleGiftCode;
    private LocalDateTime giftCodeSendDate;
    private String policySaleGiftCodeSendStatus;

    public static PolicySaleCustomerResponse fromModel(PolicySaleCustomer policySaleCustomers){
        return PolicySaleCustomerResponse.builder()
                .id(policySaleCustomers.getId())
                .policySaleGiftCode(policySaleCustomers.getPolicySaleGiftCode()!= null ?
                        policySaleCustomers.getPolicySaleGiftCode().getCode() : null)
                .giftCodeSendDate(policySaleCustomers.getGiftCodeSendDate())
                .policySaleGiftCodeSendStatus(policySaleCustomers.getPolicySaleGiftCodeSendStatus() != null
                        ? policySaleCustomers.getPolicySaleGiftCodeSendStatus().equals(PolicySaleGiftCodeSendStatusEnum.FAILED)?
                        policySaleCustomers.getNotificationDeliveryDescription() :
                        policySaleCustomers.getPolicySaleGiftCodeSendStatus().getValue()
                        : null)
                .customerNo(policySaleCustomers.getCustomerNo())
                .build();
    }

    public static List<PolicySaleCustomerResponse> fromListModel(List<PolicySaleCustomer> policySaleCustomers){
        return policySaleCustomers.stream()
                .map(PolicySaleCustomerResponse::fromModel)
                .collect(Collectors.toList());
    }
}
