/* odeon_sukruo created on 29.04.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.campaign.port */

package com.anadolusigorta.campaignmanagement.domain.campaign.port;


import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignAttribute;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateCampaignAttribute;

import java.util.List;

public interface CampaignAttributeRepository {

	List<CampaignAttribute>  getCampaignAttributesByCampaignTypeIdAndCampaignStructureId(Long campaignTypeId, Long structureId, Long attributeTypeId);

	CampaignAttribute getCampaignAttributeById(Long id);

	List<CampaignAttribute> getChildCampaignAttributesParentById(Long id);

	List<CampaignAttribute> getAllCampaignAttributes();

	CampaignAttribute saveCampaignAttribute(CreateCampaignAttribute campaignAttribute);

	CampaignAttribute deleteCampaignAttribute(Long id);

    List<CampaignAttribute> getCampaignMatAttributes();

	List<CampaignAttribute> getCampaignRewardAttributes();
}
