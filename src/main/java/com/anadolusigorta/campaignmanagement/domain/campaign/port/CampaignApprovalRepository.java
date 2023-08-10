package com.anadolusigorta.campaignmanagement.domain.campaign.port;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignApproval;

import java.util.List;

public interface CampaignApprovalRepository {

    List<CampaignApproval> getCampaignApprovalHistoryByCampaignId(Long campaignId);





}
