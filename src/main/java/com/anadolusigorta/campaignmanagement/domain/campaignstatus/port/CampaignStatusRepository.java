package com.anadolusigorta.campaignmanagement.domain.campaignstatus.port;

import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatus;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatusEnum;

import java.util.List;

public interface CampaignStatusRepository {

    List<CampaignStatus> getCampaignStatuses();

}
