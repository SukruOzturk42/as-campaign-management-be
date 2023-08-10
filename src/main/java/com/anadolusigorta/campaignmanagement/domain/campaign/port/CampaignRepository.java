package com.anadolusigorta.campaignmanagement.domain.campaign.port;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.*;

import java.util.List;

public interface CampaignRepository {

    Campaign saveCampaign(CreateCampaign createCampaign);

    CampaignInformation findCampaignInformationByCampaignId(Long id);

    CampaignInformation findCampaignInformationByCampaignIdAndVersionId(Long id,Long version);

    CampaignInformation findCampaignInformationByCampaignIdAndVersion(Long id,Long version);

    List<CampaignInformation> findAllCampaignInformationByCampaignId(Long campaignId);

    List<CampaignInformation> findAllCampaignInformationByResendActive();


    List<CampaignVersion> getCampaignVersions(Long campaignId);

    Campaign findByCampaignIdAndCampaignVersionAndRuleGroupId(Long campaignId,Long campaignVersion,Long ruleGroupId);

}
