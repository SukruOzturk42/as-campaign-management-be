package com.anadolusigorta.campaignmanagement.domain.campaignstatus.port;

import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignApprovalStatus;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignApprovalStatusEnum;

import java.util.List;

public interface CampaignApprovalStatusRepository {

    List<CampaignApprovalStatus> getApprovalStatuses();

}
