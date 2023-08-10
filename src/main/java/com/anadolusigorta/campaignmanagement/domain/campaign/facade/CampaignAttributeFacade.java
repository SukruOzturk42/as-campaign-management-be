/* odeon_sukruo created on 29.04.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.campaign.facade */

package com.anadolusigorta.campaignmanagement.domain.campaign.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignAttribute;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateCampaignAttribute;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignAttributeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignAttributeFacade {

	private final CampaignAttributeRepository campaignAttributeRepository;


	public List<CampaignAttribute> getCampaignAttributes(Long campaignTypeId, Long campaignStructureId,  Long attributeTypeId){
		return campaignAttributeRepository
				.getCampaignAttributesByCampaignTypeIdAndCampaignStructureId(campaignTypeId,campaignStructureId, attributeTypeId);

	}



	public CampaignAttribute getCampaignAttributeById(Long id){
		return campaignAttributeRepository
				.getCampaignAttributeById(id);

	}

	public List<CampaignAttribute> getChildCampaignAttributeByParentId(Long id){
		return campaignAttributeRepository
				.getChildCampaignAttributesParentById(id);

	}

	public List<CampaignAttribute> getAllCampaignAttributes(){
		return campaignAttributeRepository.getAllCampaignAttributes();

	}

	public CampaignAttribute saveCampaignAttribute(CreateCampaignAttribute campaignAttribute){
		return campaignAttributeRepository.saveCampaignAttribute(campaignAttribute);
	}

	public CampaignAttribute deleteCampaignAttribute(Long id){
		return campaignAttributeRepository.deleteCampaignAttribute(id);
	}

	public List<CampaignAttribute> getCampaignMatAttributes() {
		return campaignAttributeRepository.getCampaignMatAttributes();
	}

	public List<CampaignAttribute> getCampaignRewardAttributes() {
		return campaignAttributeRepository.getCampaignRewardAttributes();
	}
}
