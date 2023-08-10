/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.product.rest.dto */

package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.contact.facade.ContactFacade;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactCampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.CampaignFindOperation;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class CustomerCampaignsRequest {
	@NotNull
	private String contactNumber;

	@NotNull
	private String transactionId;

	@NotNull
	private LocalDateTime date;


	private Long campaignId;


	private Long campaignVersion;


	private Long ruleGroupId;


	private Long grossPremium;

	private Long netPremium;

	@NotNull
	private List<ProductRequest> parameters;




	@Setter
	@Getter
	public static class ProductRequest {
		@NotNull
		private Map<String,Object> parametersInfo;
		private List<Map<String,String>> subProducts;
	}

	public CampaignFindOperation toModel(){
		return CampaignFindOperation.builder()
				.contactCampaignInformationList(toCampaignListModel())
				.transactionId(transactionId)
				.contactNumber(contactNumber)
				.request(this)
				.build();
	}

	public List<ContactCampaignInformation> toCampaignListModel(){
		log.info(String.format("Find request :%s",this));
		if(parameters.size()>1){
			throw new CampaignManagementException("max.product.count.reached");
		}
		return parameters.stream()
				.map(item-> ContactCampaignInformation.builder()
						.contactNumber(contactNumber)
						.transactionId(transactionId)
						.campaignId(campaignId)
						.ruleGroupId(ruleGroupId)
						.campaignVersion(campaignVersion)
						.parametersInfo(toParametersInfoModel(item.getParametersInfo()))
						.subProducts(item.subProducts)
						.build())
				.collect(Collectors.toList());
	}

	private Map<String,Object> toParametersInfoModel(Map<String,Object> parametersInfo){

		var mandatoryKey=parametersInfo.keySet().stream()
				.filter(item->item.equals(Constants.MANDATORY_PARAMETER_KEY))
				.findAny();
		if(mandatoryKey.isEmpty()){
			throw new CampaignManagementException("mandatory.key.not.found");
		}



		var birthDateParam=parametersInfo.get(Constants.BIRTH_DATE_PARAM_KEY);
		if(birthDateParam!=null){
			parametersInfo.put(Constants.CONTACT_AGE_PARAM_KEY, ContactFacade.calculateContactAge(
					(String) birthDateParam));
		}

		var agentCode=parametersInfo.get(Constants.AGENCY_CODE_PARAM_KEY);
		if(agentCode!=null){
			parametersInfo.put(Constants.AGENCY_CODE_RANGE_PARAM_KEY, agentCode);
		}
		return parametersInfo;
	}





}
