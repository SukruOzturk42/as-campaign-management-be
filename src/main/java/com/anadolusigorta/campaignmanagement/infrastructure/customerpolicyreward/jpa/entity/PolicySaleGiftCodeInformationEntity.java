package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleAttribute;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCodeInformationDetail;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.RewardGiftTicketTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.enums.UploadFileEnum;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "policy_sale_gift_code_information")
public class PolicySaleGiftCodeInformationEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "policy_sale_reward_company_information_id")
    private PolicySaleRewardCompanyInformationEntity policySaleRewardCompanyInformationEntity;

    @Enumerated(EnumType.STRING)
    private UploadFileEnum uploadFileEnum;

    @ManyToOne
    @JoinColumn(name = "policy_sale_gift_code_reward_type_id")
    private PolicySaleRewardGiftTicketTypeEntity policySaleGiftCodeRewardType;

    @OneToMany(mappedBy = "policySaleGiftCodeInformation", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<PolicySaleGiftCodeInformationDetailEntity> policySaleGiftCodeInformationDetails = new ArrayList<>();

    public PolicySaleGiftCodeInformation toModel() {
        return PolicySaleGiftCodeInformation.builder()
                .id(super.getId())
                .rewardCompanyInformation(policySaleRewardCompanyInformationEntity.toModel())
                .uploadFileEnum(uploadFileEnum != null ? uploadFileEnum.getValue() : "")
                .rewardGiftTicketType(policySaleGiftCodeRewardType.toModel())
                .policySaleGiftCodeInformationDetails(policySaleGiftCodeInformationDetails.stream().map(PolicySaleGiftCodeInformationDetailEntity::toModel)
                        .sorted(Comparator.comparing(PolicySaleGiftCodeInformationDetail::getCreatedAt)).collect(Collectors.toList()))
                .codeExpirationDate(null)
                .build();
    }
}
