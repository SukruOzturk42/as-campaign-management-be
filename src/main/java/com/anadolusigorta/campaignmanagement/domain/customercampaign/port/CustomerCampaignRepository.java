package com.anadolusigorta.campaignmanagement.domain.customercampaign.port;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.*;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CustomerCampaignRepository {

    List<Campaign> findAll();

    Campaign findByCampaignId(Long campaignId);

    List<CampaignInformation> findContactCampaignsInformation(CampaignCriteria campaignCriteria);

    List<CampaignInformation> findContactCampaignsInformation(String campaignStatusType, Long campaignTypeId);

    PageContent<CampaignInformation> findContactCampaignsInformationPageable(CampaignCriteria campaignCriteria, Pageable pageable);

    PageContent<CampaignVersion> findContactCampaignsVersionPageable(CampaignCriteria campaignCriteria, Pageable pageable);


    Campaign findByCampaignIdAndIsActiveTrue(Long campaignId);

    List<CampaignInformation> getDiscountCodeDefinedParticipantCampaigns();

    AvailableCampaign findAvailableCampaignByCampaignIdAndRuleGroupId(Long campaignId,Long ruleGroupId);

}
