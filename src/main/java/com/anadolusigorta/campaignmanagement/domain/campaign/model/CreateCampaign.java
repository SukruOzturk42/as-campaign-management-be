package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.operator.ConjunctionOperatorEnum;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.DataType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCampaign {

    private Long id;

    private Long actionId;

    private CampaignInformation campaignInformation;

    private List<CampaignRuleGroup> campaignRuleGroups;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CampaignInformation {

        private Long campaignId;

        private Long actionId;


        private Long campaignGroupId;

        private String campaignName;

        private String campaignTitle;

        private Long campaignTypeId;

        private List<String> tags;

        private LocalDateTime campaignStartDate;

        private LocalDateTime campaignEndDate;

        private LocalDateTime versionStartDate;

        private String actionDescription;

        private String shortDescription;

        private Boolean hasCustomerLimit;

        private Integer customerLimitSize;

        private Long campaignStatusId;

        private Long campaignApprovalStatusId;

        @Builder.Default
        private CampaignCreateModeType createMode=CampaignCreateModeType.INITIAL_CREATE;

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CampaignRuleGroup {
        private Long ruleGroupId;
        private String name;
        private ConjunctionOperatorEnum conjunctionOperator;
        private List<CampaignAttribute> attributes;
        private RuleGroupReward ruleGroupReward;
        private CampaignRuleGroup ownerProduct;
        private CampaignRuleGroup relatedCooperation;
        private CampaignRuleGroup contactProduct;
        private CampaignRuleGroup matRuleGroup;
        private CampaignRuleGroup isBankRuleGroup;
        private Long contactGroupId;
        private Long taskGroupId;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CampaignAttributeRuleGroup {
        private Long attributeRuleGroupId;
        private String name;
        private ConjunctionOperatorEnum conjunctionOperator;
        private List<CampaignAttribute> attributes;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CampaignAttribute {

        private Long ruleAttributeId;
        private Long attributeId;
        private Long structureId;
        private OperatorEnum operator;
        private AttributeTypeEnum type;
        private List<String> value;
        private CampaignAttributeRuleGroup subProduct;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CampaignRuleAttribute {
        private Long ruleAttributeId;
        private Long  parameterId;
        private String parameter;
        private OperatorEnum operator;
        private List<String> value=new ArrayList<>();
        private AttributeTypeEnum attributeType;
        private DataType dataType;
        private Long order;
        private CampaignAttributeRuleGroup subProduct;


    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RuleGroupReward {
        private Long rewardId;
        private RuleGroupRewardDiscount discount;
        private RuleGroupRewardGift gift;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RuleGroupRewardDiscount {
        private String name;
        private String value;
        private Long discountTypeId;
        private Long discountDetailTypeId;
        private Long discountKindId;
        private Long discountCodeInformationId;
        private String coverCodeDiscountValue;
        private Long coverCodeTypeId;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RuleGroupRewardGift {

        private Long rewardGiftId;

        private String value;

        private String name;

        private Long totalProductCount;

        private Long customerProductCount;

        private Long totalCustomerCount;

        private Long rewardGiftTypeId;

        private Long rewardGiftCodeInformationId;

        private Long companyInformationId;

        private Long rewardGiftDeliveryTypeId;

        private Long rewardGiftPaymentTypeId;

        private Long rewardGiftDeliveryStartTypeId;

        private Long rewardGifKindId;

        private Long productDeliveryOrder;

        private Long rewardProductId;

        private LocalDateTime lastSendTime;

        private Integer dayAfterDeliveryStart;

        private Long sendMethodTypeId;

        private String rewardGiftTemplateId;

        private Long rewardRoleId;

        private List<CampaignRuleAttribute> sendRules;

    }

}
