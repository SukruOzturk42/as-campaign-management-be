/* odeon_sukruo created on 21.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.product.facade */

package com.anadolusigorta.campaignmanagement.domain.contact.facade;


import com.anadolusigorta.campaignmanagement.domain.campaign.facade.CampaignFacade;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.*;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.customercampaign.facade.CustomerCampaignFacade;
import com.anadolusigorta.campaignmanagement.domain.handler.CampaignHandler;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactCampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.handler.CampaignRuleGroupHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Literal;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactFacade {

	private final CustomerCampaignFacade customerCampaignFacade;
	private final CampaignFacade campaignFacade;
	private final CampaignHandler campaignHandler;
	private final CampaignRuleGroupHandler ruleGroupHandler;

	public Map<String, List<AvailableCampaign>> getAvailableCampaignForContact(String contactNo,
			List<ContactCampaignInformation> contactCampaignInformationList) {


		var contactCampaigns = new HashMap<String, List<AvailableCampaign>>();

		var campaigns = customerCampaignFacade.getActiveCampaigns();



		for (ContactCampaignInformation contactCampaignInformation : contactCampaignInformationList) {


			var matchedCampaigns = getAvailableCampaigns(campaigns, contactCampaignInformation);


			contactCampaigns.put(contactCampaignInformation.getParametersInfo().get(
					Constants.MANDATORY_PARAMETER_KEY).toString(), matchedCampaigns);
		}
		log.info("Campaign Search is ended: "+ LocalDateTime.now());

		return contactCampaigns;
	}


	public List<CampaignInformation> searchCampaignInformation(CampaignSearch campaignSearch) {
		List<Campaign> campaigns = customerCampaignFacade.getActiveCampaigns();
			campaigns=campaigns.stream()
					.filter(item->campaignSearch.getCampaignTypes()
							.contains(item.getCampaignInformation().getCampaignType().getId()))
					.collect(Collectors.toList());
		if(campaignSearch.getParameters() != null && !campaignSearch.getParameters().isEmpty()){
			return searchAvailableCampaigns(campaigns, campaignSearch.getParameters()).stream()
					.map(AvailableCampaign::getCampaignInformation)
					.collect(Collectors.toList());
		}
		return campaigns
				.stream()
				.map(Campaign::getCampaignInformation)
				.sorted(Comparator
						.comparing(CampaignInformation::getCampaignId).reversed())
				.collect(Collectors.toList());
	}

	public AvailableCampaign getAvailableCampaignByCampaignIdAndRuleGroupId(Long campaignId,Long ruleGroupId){
       return customerCampaignFacade.getAvailableCampaignByCampaignIdAndRuleGroupId(campaignId,ruleGroupId);
	}

	public AvailableCampaign validateCampaignForProduct(ContactCampaignInformation contactCampaignInformation){

		var campaign=campaignFacade
				.getByCampaignIdAndCampaignVersionAndRuleGroupId(contactCampaignInformation.getCampaignId()
						,contactCampaignInformation.getCampaignVersion(),contactCampaignInformation.getRuleGroupId());

		var ruleGroup=campaign.getRuleGroups()
				.stream()
				.filter(item->item.getRuleGroupId().equals(contactCampaignInformation.getRuleGroupId()))
				.findAny()
				.orElseThrow(()->new CampaignManagementException("rule.group.not.found"));



		var ruleParams=new ArrayList<CampaignRuleAttribute>();

		collectRuleGroup(ruleGroup,ruleParams);
		var params=ruleParams.stream()
				.filter(item->contactCampaignInformation
						.getParametersInfo()
						.containsKey(item.getParameter()))
				.toList();
		ruleGroup.setRules(params);

		var result=ruleGroupHandler.isMatchForProduct(ruleGroup,contactCampaignInformation.getParametersInfo());

		if(result){
			return AvailableCampaign.builder()
					.campaignInformation(campaign.getCampaignInformation())
					.ruleGroup(ruleGroup)
					.build();
		}
		else{
			throw new CampaignManagementException("contact.and.product.is.not.match");
		}

	}



	private List<AvailableCampaign> getAvailableCampaigns(List<Campaign> campaigns, ContactCampaignInformation contactCampaignInformation) {
		var matchedCampaigns = new ArrayList<AvailableCampaign>();

		for (var campaign : campaigns) {
				var matchedCampaign = campaignHandler
						.getCampaignForProduct(contactCampaignInformation, campaign);
				if (matchedCampaign != null) {
					matchedCampaigns.add(matchedCampaign);
				}
		}
		if (contactCampaignInformation.getCampaignId() != null) {
			matchedCampaigns.removeIf(item->item.getCampaignInformation().getCampaignId()
					.equals(contactCampaignInformation.getCampaignId()));
			var exVersionCampaign=campaignFacade
					.getByCampaignIdAndCampaignVersionAndRuleGroupId(contactCampaignInformation.getCampaignId()
							,contactCampaignInformation.getCampaignVersion(),contactCampaignInformation.getRuleGroupId());
			var exVersionAvailableCampaign=AvailableCampaign.builder()
					.campaignInformation(exVersionCampaign.getCampaignInformation())
					.ruleGroup(!exVersionCampaign.getRuleGroups().isEmpty()?
							exVersionCampaign.getRuleGroups().get(0):null)
					.build();

			matchedCampaigns.add(exVersionAvailableCampaign);

		}

		return matchedCampaigns;
	}

	private List<AvailableCampaign> searchAvailableCampaigns(List<Campaign> campaigns,Map<String,Object> parametersInfo) {
		var availableCampaigns = new ArrayList<AvailableCampaign>();
		for (var campaign : campaigns) {
			var matchedCampaign = campaignHandler.executeSearchOperation(parametersInfo, campaign);
			if (matchedCampaign != null) {
				availableCampaigns.add(matchedCampaign);
			}
		}
		return availableCampaigns;
	}



	public static int calculateContactAge(String contactBirthDate){
		var customerAge = LocalDateTime.now().getYear() - LocalDateTime.parse(contactBirthDate.trim()).getYear();
		var isMonth = LocalDateTime.parse(contactBirthDate.trim()).getMonthValue() - LocalDateTime.now().getMonthValue();
		if(isMonth > 0) {
			customerAge -= 1;
		}
		return customerAge;
	}

	public void collectRuleGroup(CampaignRuleGroup campaignRuleGroup,List<CampaignRuleAttribute> campaignRuleAttributes){

		for(var ruleAtr:campaignRuleGroup.getRules()){
			if(ruleAtr.getRule()==null){
				campaignRuleAttributes.add(ruleAtr);

			}else {
				collectRuleGroup(ruleAtr.getRule(),campaignRuleAttributes);
			}
		}

	}


}
