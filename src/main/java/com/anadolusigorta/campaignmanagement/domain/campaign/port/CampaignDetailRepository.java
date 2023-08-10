package com.anadolusigorta.campaignmanagement.domain.campaign.port;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignDetail;

import java.util.List;

public interface CampaignDetailRepository {

    List<CampaignDetail> saveOrUpdateCampaignDetail(CampaignDetail campaignDetail);

    List<CampaignDetail> deleteCampaignDetailByCampaignDetail(CampaignDetail campaignDetail);

    List<CampaignDetail> getCampaignDetailsByCampaignId(Long campaignId);
    List<CampaignDetail> getCampaignDetailsByCampaignIdAndSalesChannel(Long campaignId,String salesChannelType);

}
