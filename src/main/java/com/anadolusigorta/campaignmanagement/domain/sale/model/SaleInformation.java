package com.anadolusigorta.campaignmanagement.domain.sale.model;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleGroup;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionOperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleInformation {

    private String transactionId;
    private Long id;
    private CampaignInformation campaignInformation;
    private CampaignRuleGroup campaignRuleGroup;
    private SoldPolicyDetail soldPolicyDetail;
    private String contactNumber;
    private LocalDateTime createDate;
    private SaleTransactionOperationType requestType;
    private String campaignCode;
    private SaleRewardGift saleRewardGift;
    private List<SaleInformation> insuredSaleInformation;

}
