package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignCreateModeType;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateCampaign.*;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateCampaign;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.operator.ConjunctionOperatorEnum;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.DataType;
import com.anadolusigorta.campaignmanagement.infrastructure.validation.conditionalvalidation.ConditionalValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class CreateCampaignRequest {

    private Long id;

    private Long actionId;

    @Valid
    private CampaignInformationRequest campaignInformation;

    private List<CampaignRuleGroupRequest> campaignRuleGroups;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ConditionalValidation(
            conditionalProperty = "hasCustomerLimit", values = {"true"},
            requiredProperties = {"customerLimitSize"},
            message = "Customer Limit Size is need if has customer limit"
    )
    public static class CampaignInformationRequest {

        private Long actionId;

        private Long  campaignGroupId;

        private Long campaignStatusId;

        private Long campaignApprovalStatusId;

        private String campaignName;

        private String campaignTitle;

        private Long campaignTypeId;

        private List<String> tags;


        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        private LocalDateTime campaignStartDate;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        private LocalDateTime campaignEndDate;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        private LocalDateTime versionStartDate;

        private String actionDescription;

        private String shortDescription;

        private Boolean hasCustomerLimit;

        @Min(1)
        @PositiveOrZero
        private Integer customerLimitSize;

        private CampaignCreateModeType createMode=CampaignCreateModeType.INITIAL_CREATE;


    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CampaignRuleGroupRequest {
        private String name;
        private ConjunctionOperatorEnum conjunctionOperator;
        private List<CampaignAttributeRequest> attributes;
        private RuleGroupRewardRequest ruleGroupReward;
        private CampaignRuleGroupRequest ownerProduct;
        private CampaignRuleGroupRequest relatedCooperation;
        private CampaignRuleGroupRequest contactProduct;
        private CampaignRuleGroupRequest matRuleGroup;
        private CampaignRuleGroupRequest isBankRuleGroup;
        private Long contactGroupId;
        private Long taskGroupId;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CampaignAttributeRuleGroupRequest {
        private String name;
        private ConjunctionOperatorEnum conjunctionOperator;
        private List<CampaignAttributeRequest> attributes;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CampaignAttributeRequest {

        private Long attributeId;
        private Long structureId;
        private OperatorEnum operator;
        private AttributeTypeEnum type;
        private List<String> value;
        private CampaignAttributeRuleGroupRequest subProduct;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RuleGroupRewardRequest {

        private RuleGroupRewardDiscountRequest  discount;
        private RuleGroupRewardGiftRequest gift;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RuleGroupRewardDiscountRequest {

        private String value;
        private String name;
        private Long discountCodeInformationId;
        private Long discountTypeId;
        private Long discountDetailTypeId;
        private Long discountKindId;
        private String coverCodeDiscountValue;
        private Long coverCodeTypeId;

    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RuleGroupRewardGiftRequest {

        private Long id;

        private String value;

        private String name;

        private Long totalProductCount;

        private Long totalCustomerProductCount;

        private Long customerCount;

        private Long companyInformationId;

        private Long rewardGiftTypeId;

        private Long rewardGiftCodeInformationId;

        private Long rewardGiftDeliveryTypeId;

        private Long rewardGiftPaymentTypeId;

        private Long rewardGiftDeliveryStartTypeId;

        private Long rewardGifKindId;

        private Long productDeliveryOrder;

        private Long rewardProductId;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        private LocalDateTime lastSendTime;

        private Integer dayAfterDeliveryStart;

        private Long sendMethodTypeId;

        private String rewardGiftTemplateId;

        private Long rewardRoleId;

        private List<CampaignRuleAttributeRequest> sendRules;
    }
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RuleGroupCustomerFile {

        private String data;

        private String fileName;

        private Long size;

        private String fileType;

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CampaignRuleAttributeRequest {
        private Long ruleAttributeId;
        private Long  parameterId;
        private String parameter;
        private OperatorEnum operator;
        private List<String> value=new ArrayList<>();
        private AttributeTypeEnum attributeType;
        private DataType dataType;
        private Long order;


    }

    public CreateCampaign toModel() {

        if(campaignInformation.campaignStartDate == null){
            campaignInformation.campaignStartDate = LocalDateTime.now().plusHours(1L);
        }

        if(campaignInformation.campaignEndDate == null){
            campaignInformation.campaignEndDate = LocalDateTime.now().toLocalDate().atTime(23,59,59);
        }

        if(campaignInformation.campaignEndDate.isBefore(campaignInformation.campaignStartDate)){
            throw new CampaignManagementException("campaign.end.date.before.campaign.start.date");
        }
        if(id!=null && campaignInformation.createMode!=CampaignCreateModeType.COPY_CAMPAIGN){
            if(actionId==null){
                throw new CampaignManagementException("campaign.action.should.not be.empty");

            }
        }


        return CreateCampaign.builder()
                .id(id)
                .actionId(actionId)
                .campaignInformation(CampaignInformation.builder()
                        .campaignId(id)
                        .actionId(campaignInformation.actionId)
                        .campaignApprovalStatusId(campaignInformation.campaignApprovalStatusId)
                        .campaignStatusId(campaignInformation.campaignStatusId)
                        .campaignGroupId(campaignInformation.campaignGroupId)
                        .campaignName(campaignInformation.campaignName)
                        .campaignTitle(campaignInformation.campaignTitle)
                        .campaignTypeId(campaignInformation.campaignTypeId)
                        .tags(campaignInformation.tags)
                        .campaignStartDate(campaignInformation.campaignStartDate!=null
                                ?campaignInformation.campaignStartDate
                                : LocalDateTime.now())
                        .campaignEndDate(campaignInformation.campaignEndDate!=null?
                                campaignInformation.campaignEndDate
                                :LocalDateTime.now().toLocalDate().atTime(LocalTime.MAX))
                        .versionStartDate(campaignInformation.versionStartDate)
                        .actionDescription(campaignInformation.actionDescription)
                        .shortDescription(campaignInformation.shortDescription)
                        .hasCustomerLimit(campaignInformation.hasCustomerLimit)
                        .customerLimitSize(campaignInformation.customerLimitSize)
                        .createMode(campaignInformation.createMode)
                        .build())
                .campaignRuleGroups(campaignRuleGroups.stream()
                        .map(this::mapToCampaignRuleGroupModel)
                        .collect(Collectors.toList()))
                .build();
    }

    private CampaignRuleGroup mapToCampaignRuleGroupModel(CampaignRuleGroupRequest campaignRuleGroupRequest){
        return CampaignRuleGroup.builder()
                .name(campaignRuleGroupRequest.name)
                .attributes(campaignRuleGroupRequest
                        .attributes != null ? campaignRuleGroupRequest
                        .attributes.stream()
                        .map(this::mapToCampaignAttributeModel)
                        .collect(Collectors.toList()) : null)
                .ruleGroupReward(campaignRuleGroupRequest.ruleGroupReward!=null?
                        toRewardModel(campaignRuleGroupRequest.ruleGroupReward):
                        RuleGroupReward.builder().build())
                .conjunctionOperator(campaignRuleGroupRequest.conjunctionOperator)
                .ownerProduct(campaignRuleGroupRequest.getOwnerProduct()!=null &&
                        campaignRuleGroupRequest.getOwnerProduct().getName()!=null?
                        mapToCampaignRuleGroupModel(campaignRuleGroupRequest.getOwnerProduct())
                        :null)
                .relatedCooperation(campaignRuleGroupRequest.getRelatedCooperation()!=null &&
                        campaignRuleGroupRequest.getRelatedCooperation().getName()!=null?
                        mapToCampaignRuleGroupModel(campaignRuleGroupRequest.getRelatedCooperation())
                        :null)
                .contactProduct(campaignRuleGroupRequest.getContactProduct()!=null &&
                        campaignRuleGroupRequest.getContactProduct().getName()!=null?
                        mapToCampaignRuleGroupModel(campaignRuleGroupRequest.getContactProduct())
                        :null)
                .matRuleGroup(campaignRuleGroupRequest.getMatRuleGroup()!=null &&
                        campaignRuleGroupRequest.getMatRuleGroup().getName()!=null?
                        mapToCampaignRuleGroupModel(campaignRuleGroupRequest.getMatRuleGroup())
                        :null)
                .isBankRuleGroup(campaignRuleGroupRequest.getIsBankRuleGroup()!=null &&
                        campaignRuleGroupRequest.getIsBankRuleGroup().getName()!=null?
                        mapToCampaignRuleGroupModel(campaignRuleGroupRequest.getIsBankRuleGroup())
                        :null)
                .contactGroupId(campaignRuleGroupRequest.contactGroupId)
                .taskGroupId(campaignRuleGroupRequest.taskGroupId)
                .build();
    }

    private CampaignAttributeRuleGroup mapToCampaignAttributeRuleGroupModel(CampaignAttributeRuleGroupRequest campaignAttributeRuleGroupRequest){
        return CampaignAttributeRuleGroup.builder()
                .name(campaignAttributeRuleGroupRequest.name)
                .attributes(campaignAttributeRuleGroupRequest
                        .attributes.stream()
                        .map(this::mapToCampaignAttributeModel)
                        .collect(Collectors.toList()))
                .conjunctionOperator(campaignAttributeRuleGroupRequest.conjunctionOperator)
                .build();
    }

    private RuleGroupReward toRewardModel(RuleGroupRewardRequest groupRewardRequest){

        return  RuleGroupReward.builder()
                .discount(groupRewardRequest.discount!=null &&
                        (groupRewardRequest.discount.discountKindId!=null ||
                        groupRewardRequest.discount.discountCodeInformationId!=null)
                        ?
                toRewardDiscountModel(groupRewardRequest.discount)
                        :null)
                .gift(groupRewardRequest.gift!=null &&
                        groupRewardRequest.gift.rewardGiftTypeId!=null?
                        toRewardGiftModel(groupRewardRequest.gift)
                        :null)
                .build();
    }

    private RuleGroupRewardDiscount toRewardDiscountModel(RuleGroupRewardDiscountRequest discountRequest) {
        return RuleGroupRewardDiscount.builder()
                .name(discountRequest.name)
                .discountDetailTypeId(discountRequest.discountDetailTypeId)
                .discountTypeId(discountRequest.discountTypeId)
                .discountKindId(discountRequest.discountKindId)
                .discountCodeInformationId(discountRequest.discountCodeInformationId)
                .value(discountRequest.discountKindId!=null ?
                        discountRequest.value : null)
                .coverCodeDiscountValue(discountRequest.coverCodeDiscountValue)
                .coverCodeTypeId(discountRequest.coverCodeTypeId)
                .build();
    }

    private RuleGroupRewardGift toRewardGiftModel(RuleGroupRewardGiftRequest giftRequest) {
        return RuleGroupRewardGift.builder()
                .name(giftRequest.getName())
                .rewardGifKindId(giftRequest.rewardGifKindId)
                .rewardGiftTypeId(giftRequest.rewardGiftTypeId)
                .rewardGiftPaymentTypeId(giftRequest.rewardGiftPaymentTypeId)
                .rewardGiftDeliveryStartTypeId(giftRequest.rewardGiftDeliveryStartTypeId)
                .rewardGiftDeliveryTypeId(giftRequest.rewardGiftDeliveryTypeId)
                .totalCustomerCount(giftRequest.customerCount)
                .customerProductCount(giftRequest.totalCustomerProductCount == null ? 1L
                        : giftRequest.totalCustomerProductCount)
                .totalProductCount(giftRequest.totalProductCount)
                .productDeliveryOrder(giftRequest.productDeliveryOrder)
                .rewardProductId(giftRequest.rewardProductId)
                .rewardGiftCodeInformationId(giftRequest.rewardGiftCodeInformationId)
                .dayAfterDeliveryStart(giftRequest.dayAfterDeliveryStart)
                .sendMethodTypeId(giftRequest.sendMethodTypeId)
                .rewardGiftTemplateId(giftRequest.rewardGiftTemplateId)
                .lastSendTime(giftRequest.lastSendTime)
                .value(giftRequest.value)
                .rewardRoleId(giftRequest.getRewardRoleId())
                .sendRules(giftRequest.sendRules!=null ?
                        giftRequest.sendRules.stream()
                                .map(this::mapToCampaignRuleAttributeModel)
                                .collect(Collectors.toList()) :null)
                .build();
    }

    private CampaignAttribute mapToCampaignAttributeModel(CampaignAttributeRequest campaignAttributeRequest){
        return CampaignAttribute.builder()
                .attributeId(campaignAttributeRequest.attributeId)
                .operator(campaignAttributeRequest.operator)
                .structureId(campaignAttributeRequest.structureId)
                .type(campaignAttributeRequest.type)
                .value(campaignAttributeRequest.value)
                .subProduct(campaignAttributeRequest.subProduct!=null &&
                        (campaignAttributeRequest.subProduct.name!=null ||
                                campaignAttributeRequest.subProduct.attributes!=null)?
                        mapToCampaignAttributeRuleGroupModel(campaignAttributeRequest.subProduct)
                        :null)
                .build();
    }

    private CampaignRuleAttribute mapToCampaignRuleAttributeModel(CampaignRuleAttributeRequest campaignAttributeRequest){
        return CampaignRuleAttribute.builder()
                .parameterId(campaignAttributeRequest.parameterId)
                .operator(campaignAttributeRequest.operator)
                .attributeType(campaignAttributeRequest.attributeType)
                .value(campaignAttributeRequest.value)
                .ruleAttributeId(campaignAttributeRequest.getRuleAttributeId())
                .attributeType(campaignAttributeRequest.attributeType)
                .dataType(campaignAttributeRequest.dataType)
                .order(campaignAttributeRequest.order)
                .build();
    }



}
