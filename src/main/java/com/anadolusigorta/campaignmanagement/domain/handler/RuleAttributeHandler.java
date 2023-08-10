/* odeon_sukruo created on 21.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.handler */

package com.anadolusigorta.campaignmanagement.domain.handler;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleAttribute;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorDataTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RuleAttributeHandler {

	private final OperatorHandlerFactory operatorHandlerFactory;

	public boolean isMatchValue(Object requestParamValue, CampaignRuleAttribute campaignRuleAttribute){
		var requestDataType=requestRuleParamDataType(requestParamValue);
		if (requestDataType == OperatorDataTypeEnum.COLLECTION) {
			var collectionRequest = (List<String>)requestParamValue;
			return isMatchRequestCollection(collectionRequest, campaignRuleAttribute);
		}
		return operatorHandlerFactory
				.isMatchParam(requestParamValue, campaignRuleAttribute);

	}

	public OperatorDataTypeEnum requestRuleParamDataType(Object requestValue){
		try{
			if(isCollection(requestValue)){
				return OperatorDataTypeEnum.COLLECTION;
			}else {
				return OperatorDataTypeEnum.STRING;
			}

		}catch (Exception e){
			log.info("error occurred when matching operation="+e.getMessage());
			return OperatorDataTypeEnum.STRING;
		}
	}
	public boolean isMatchRequestCollection(List<String> requestValues, CampaignRuleAttribute campaignRuleAttribute){

		var ruleValues=campaignRuleAttribute.getValue()
				.stream()
				.map(String::trim)
				.collect(Collectors.toList());
		var intersect=requestValues.stream()
				.distinct()
				.filter(ruleValues::contains)
				.collect(Collectors.toSet());
		var result=campaignRuleAttribute.getOperator().equals(OperatorEnum.NIN)?
				Boolean.TRUE:Boolean.FALSE;
		for(var request:intersect){
			result= operatorHandlerFactory
					.isMatchParam(request, campaignRuleAttribute);
			if(result.equals(Boolean.TRUE)){
				break;
			}
		}
		return result;

	}
	private  static boolean isCollection(Object ob) {
		return ob instanceof Collection || ob instanceof Map;
	}
}


