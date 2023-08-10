/* odeon_sukruo created on 3.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.campaign.model */

package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactGroup;
import com.anadolusigorta.campaignmanagement.domain.operator.ConjunctionOperatorEnum;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CampaignRuleGroup implements Serializable {

	private Long campaignId;
	private Long campaignVersionId;
	private long campaignVersion;
	private Long ruleGroupId;
	private String name;
	private ConjunctionOperatorEnum conjunctionOperator;
	private List<CampaignRuleAttribute> rules;
	private List<CampaignRuleGroup> children=new ArrayList<>();
	private CampaignReward campaignReward;
	private CampaignRuleGroup ownerProduct;
	private CampaignRuleGroup contactProduct;
	private CampaignRuleGroup matGroup;
	private CampaignRuleGroup relatedCooperation;
	private CampaignRuleGroup isBank;
	private ContactGroup contactGroup;
	private CampaignType campaignType;
	private TaskGroup taskGroup;

	public CampaignRuleAttribute getRuleAttribute(String ruleAttributeName){
	     CampaignRuleAttribute attribute = null;
		var rulesList = new ArrayList<>(this.rules);

		 if(this.relatedCooperation!=null)
			 rulesList.addAll(this.relatedCooperation.getRules());

		 if(this.ownerProduct!=null)
			 rulesList.addAll(this.ownerProduct.getRules());

		if(this.isBank!=null)
			rulesList.addAll(this.isBank.getRules());

		for(var item:rulesList){
			var at=item.getAttributeType()!=null?
					item.getAttributeType():AttributeTypeEnum.PARAMETER;

			if(at.equals(AttributeTypeEnum.PARAMETER)
			&& item.getParameter().equals(ruleAttributeName)){
				attribute=item;
			}else if(at.equals(AttributeTypeEnum.RULE)){
				attribute=item.getRule().getRuleAttribute(ruleAttributeName);
			}
		}

		return attribute;
	}

	public List<CampaignRuleAttribute> getRuleAttributes(CampaignRuleGroup ruleGroup,String ruleAttributeName){
		List<CampaignRuleAttribute> attributes = new ArrayList<>();

		for(var item:ruleGroup.getRules()){
			var at=item.getAttributeType()!=null?
					item.getAttributeType():AttributeTypeEnum.PARAMETER;

			if(at.equals(AttributeTypeEnum.PARAMETER)
					&& item.getParameter().equals(ruleAttributeName)){
				attributes.add(item);
			}else if(at.equals(AttributeTypeEnum.RULE)){
				attributes.add(item.getRule().getRuleAttribute(ruleAttributeName));
			}
		}

		return attributes;
	}

	public Boolean isCrossSale(){
		if(ownerProduct!=null){
			var attributes=getRuleAttributes(ownerProduct,Constants.OWNER_PRODUCT_PARAMETER_NAME);
			return attributes.stream()
					.anyMatch(item->item.getOperator().equals(OperatorEnum.IN));
		}
		return false;
	}

	public Boolean isRelatedCampaign(){
		return relatedCooperation!=null;
	}

	public Boolean isSaleTaskCampaign(){
		return taskGroup!=null;
	}

	public Boolean isIsBankCampaign(){
		return isBank!=null;
	}

	public List<String> getAttributeValues(String attributeValue){
		var attribute=getRuleAttribute(attributeValue);
		return attribute!=null?attribute.getValue():null;
	}
	@JsonIgnore
	public Boolean hasDiscountRatioValue(){

		if (campaignReward.getCampaignRewardDiscount()!=null &&
				campaignReward.getCampaignRewardDiscount().getRewardDiscountKind()!=null &&
				campaignReward.getCampaignRewardDiscount().getRewardDiscountKind().getName().equals(Constants.RATIO_DISCOUNT_KIND))
		{
			return Boolean.TRUE;
		}

		return Boolean.FALSE;

	}

	@JsonIgnore
	public Boolean hasDiscount(){

		if (campaignReward.getCampaignRewardDiscount()!=null &&
				campaignReward.getCampaignRewardDiscount().getDiscountCodeInformation()!=null)
		{
			return Boolean.TRUE;
		}

		return Boolean.FALSE;

	}

	@JsonIgnore
	public Integer getDiscountRatioValue(){

		if (campaignReward.getCampaignRewardDiscount()!=null &&
				campaignReward.getCampaignRewardDiscount().getRewardDiscountKind()!=null &&
				campaignReward.getCampaignRewardDiscount().getRewardDiscountKind().getName().equals(Constants.RATIO_DISCOUNT_KIND))
		{
			return Integer.parseInt(campaignReward.getCampaignRewardDiscount().getValue());
		}

		return null;

	}
}
