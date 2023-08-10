package com.anadolusigorta.campaignmanagement.domain.campaign.port;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.*;

import java.util.List;

public interface CampaignRuleGroupRepository {


    List<CampaignRuleGroup> finCampaignRuleGroupsByCampaignId(Long campaignId);

    List<CampaignRuleGroup> findRuleGroupsWithAvailableCodeSetCampaignRuleGroupsByCampaignId(Long campaignId);

    List<CampaignRuleGroup> finCampaignRuleGroupsByVersionId(Long versionId);

    List<CampaignRuleGroup> finCampaignRuleGroupsByCampaignIdAndVersion(Long campaignId, Long version);

    CampaignRuleGroup findById(Long id);

    List<String> getCampaignRuleGroupNamesByCampaignId(Long campaignId);

}
