package com.anadolusigorta.campaignmanagement.domain.campaignstatus.port;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.ApprovalCampaignCriteria;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignCriteria;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignVersion;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CampaignSearchRepository {

    List<CampaignInformation> getContactCampaignsInformation(String campaignStatusType, Long campaignTypeId);

    PageContent<CampaignInformation> getPageableContactCampaignsInformation(CampaignCriteria approvalCampaignCriteria, Pageable pageable);

    PageContent<CampaignVersion> findContactCampaignsVersionPageable(CampaignCriteria approvalCampaignCriteria, Pageable pageable);
}
