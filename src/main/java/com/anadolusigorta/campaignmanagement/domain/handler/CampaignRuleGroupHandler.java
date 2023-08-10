/* odeon_sukruo created on 21.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.handler */

package com.anadolusigorta.campaignmanagement.domain.handler;

import com.anadolusigorta.campaignmanagement.domain.attribute.port.AttributeRepository;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.*;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactCampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactGroup;
import com.anadolusigorta.campaignmanagement.domain.contact.port.ContactGroupRepository;
import com.anadolusigorta.campaignmanagement.domain.operator.ConjunctionOperatorEnum;
import com.anadolusigorta.campaignmanagement.domain.ownerproduct.model.OwnerProduct;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskGroup;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.port.CmTaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.anadolusigorta.campaignmanagement.domain.operator.ConjunctionOperatorEnum.*;

@Service
@AllArgsConstructor
@Slf4j
public class CampaignRuleGroupHandler {

	private final RuleAttributeHandler ruleAttributeHandler;
	private final ContactGroupRepository contactGroupRepository;
	private final CmTaskRepository taskRepository;

	private final AttributeRepository attributeRepository;


	public CampaignRuleGroup findFirstMatchedCampaignRuleGroupForProduct(
			ContactCampaignInformation contactCampaignInformation, Campaign campaign){
		try{
			var productInfo= contactCampaignInformation.getParametersInfo();
			for(CampaignRuleGroup campaignRuleGroup:campaign.getRuleGroups()){
				var contactGroup=campaignRuleGroup.getContactGroup();
				var contactGroupConditionResult=contactGroupCondition(contactCampaignInformation, contactGroup);
				var taskGroupConditionResult = taskGroupCondition(contactCampaignInformation,campaignRuleGroup.getTaskGroup());


				var matRuleGroup=campaignRuleGroup.getMatGroup();
				if(matRuleGroup!=null){
					var matRules=matRuleGroup.getRules();
					campaignRuleGroup.getRules().addAll(matRules);
				}


				var parameterConditionResult=isMatchForProduct(campaignRuleGroup,productInfo);
				if(contactGroupConditionResult && parameterConditionResult && taskGroupConditionResult){
					return campaignRuleGroup;
				}
			}
			return null;
		}catch (Exception e){
			log.error(e.getMessage());
			return null;
		}

	}


	public CampaignRuleGroup findFirstContainsRuleGroupForProduct(Map<String,Object>  parameters, Campaign campaign){
		for(CampaignRuleGroup campaignRuleGroup:campaign.getRuleGroups()){
			if(isMatchSearchOperation(campaignRuleGroup,parameters)){
				return campaignRuleGroup;
			}
		}
		return null;

	}

	public boolean isMatchForProduct(CampaignRuleGroup campaignRuleGroup, Map<String,Object> productParams){
        var result=false;
		var rules=campaignRuleGroup.getRules();
		var conjunction=campaignRuleGroup.getConjunctionOperator();
		for (CampaignRuleAttribute ruleAttribute : rules) {

				result = isMatch(productParams, ruleAttribute)
						&& isMatchForSubProduct(ruleAttribute.getSubProduct(),productParams);

				Boolean conjunctionResult = getConjunctionResult(result, conjunction);
				if (conjunctionResult != null)
					return conjunctionResult;


		}
		return result;
	}

	private boolean isMatchForSubProduct(CampaignRuleGroup attributeSubProduct, Map<String,Object> productParams) {
		if (attributeSubProduct == null) {
			return true;
		}
		return isMatchForProduct(attributeSubProduct, productParams);
	}

	
	public boolean isMatchSearchOperation(CampaignRuleGroup campaignRuleGroup, Map<String,Object> productParams){
		var result=false;
		var rules=campaignRuleGroup.getRules();
		for(var key:productParams.keySet()){
			var rule=rules.stream().filter(item->{
				if(item.getRule()!=null){
					return item.getRule().getRules()
							.stream()
							.anyMatch(type->type.getParameter().equals(key));
				}else{
					return item.getParameter().equals(key);
				}
			}).findAny();
			if(rule.isEmpty()){
				return false;
			}else{
				if(rule.get().getRule()!=null){
					result= isMatchSearchOperation(rule.get().getRule(),productParams);
				}else {
					result=ruleAttributeHandler
							.isMatchValue(productParams.get(key), rule.get());
				}
			}
		}
		return result;
	}

	private boolean isMatch(Map<String, Object> productParams, CampaignRuleAttribute ruleAttribute) {

		boolean matchingResult;
		if(ruleAttribute.getRule()!=null){
			matchingResult= isMatchForProduct(ruleAttribute.getRule(),productParams);
		}else{
			var isParamsContainsRuleAttribute=productParams.containsKey(ruleAttribute.getParameter());
			if(!isParamsContainsRuleAttribute){
		        productParams.put(ruleAttribute.getParameter(),"");
			}
			matchingResult = ruleAttributeHandler
					.isMatchValue(productParams.get(ruleAttribute.getParameter()), ruleAttribute);

		}
		return matchingResult;
	}

	private Boolean getConjunctionResult(boolean result, ConjunctionOperatorEnum conjunction) {
		if(conjunction.equals(OR)){
			if(result)
				return true;
		}
		else if(conjunction.equals(AND)){
			if(!result)
				return false;
		}
		return null;
	}
	private boolean contactGroupCondition(ContactCampaignInformation contactCampaignInformation,
			ContactGroup contactGroup) {
		if(contactGroup == null){
			return true;
		}
		else{
			return contactGroupRepository
					.isContactInContactGroup(contactGroup.getContactGroupId(),contactCampaignInformation.getContactNumber());
		}

	}

	private boolean taskGroupCondition(ContactCampaignInformation contactCampaignInformation, TaskGroup taskGroup) {
		if(taskGroup == null){
			return true;
		}
		else{
			var policyType=contactCampaignInformation
					.getParametersInfo().get(Constants.MANDATORY_PARAMETER_KEY).toString();

			return taskGroup.hasPolicyType(policyType) &&
					taskRepository
							.hasContactTasks(contactCampaignInformation.getContactNumber(),taskGroup.getTaskType().getId());
		}

	}

	public List<CampaignVersion> handleFilterOperationByParameters(List<CampaignVersion> campaignVersions, CampaignCriteria campaignCriteria){
		if(campaignCriteria.getAttributeId()!=null && campaignCriteria.getValue()!=null &&
				!campaignCriteria.getValue().isEmpty()){
			var attr = attributeRepository.findById(campaignCriteria.getAttributeId());
			var params=new HashMap<String,Object>();
			campaignCriteria.getValue().stream().filter(Objects::nonNull).collect(Collectors.toList()).replaceAll(String::trim);
			params.put(attr.getName(),campaignCriteria.getValue());

			return campaignVersions.stream()
					.filter(item-> item.getCampaignRuleGroups()
							.stream().anyMatch(t-> this.isMatchSearchOperation(t,params)))
					.toList();
		}
		return campaignVersions;
	}

}
