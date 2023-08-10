/* odeon_sukruo created on 21.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.product.facade */

package com.anadolusigorta.campaignmanagement.domain.participate.facade;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.Campaign;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.customercampaign.facade.CustomerCampaignFacade;
import com.anadolusigorta.campaignmanagement.domain.handler.CampaignHandler;
import com.anadolusigorta.campaignmanagement.domain.participate.model.ParticipateCampaign;
import com.anadolusigorta.campaignmanagement.domain.participate.model.ParticipatedCustomerCampaign;
import com.anadolusigorta.campaignmanagement.domain.participate.port.ParticipateCampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class ParticipateFacade {

	private final CustomerCampaignFacade customerCampaignFacade;
	private final CampaignHandler campaignHandler;
	private final ParticipateCampaignRepository participateCampaignRepository;

	public ParticipatedCustomerCampaign participateCampaign(ParticipateCampaign participateCampaign){

		var campaign=customerCampaignFacade.getActiveCampaignByCampaignId(participateCampaign.getCampaignId());
		if(isSuitableToParticipate(participateCampaign,campaign).equals(Boolean.TRUE)){

			return participateCampaignRepository.save(participateCampaign);
		}else{
			throw new CampaignManagementException("no.right.to.participate");
		}
	}
	
	private Boolean isSuitableToParticipate(ParticipateCampaign participateCampaign, Campaign campaign){
		var campaignType=campaign.getCampaignInformation().getCampaignType();
		if(!campaignType.getName().equals(Constants.CAMPAIGN_PARTICIPATION_TYPE_NAME)){
			return false;
		}

		var suitableCampaign = campaignHandler.getAvailableCampaignToParticipate(participateCampaign.getParameters(), campaign);
		if(suitableCampaign!=null){
			var campaignInformation=suitableCampaign.getCampaignInformation();
			var numberOfParticipation=participateCampaignRepository.gerNumberOfParticipation(participateCampaign);
			if(campaignInformation.getHasCustomerLimit()!=null &&
					campaignInformation.getHasCustomerLimit().equals(Boolean.TRUE) &&
					numberOfParticipation>=campaignInformation.getCustomerLimitSize()){
				throw new CampaignManagementException("no.right.to.participate");
			}else{
				return true;
			}
		}else{
			return false;
		}
	}





}
