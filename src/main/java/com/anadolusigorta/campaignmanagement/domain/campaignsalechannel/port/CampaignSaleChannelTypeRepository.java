package com.anadolusigorta.campaignmanagement.domain.campaignsalechannel.port;

import com.anadolusigorta.campaignmanagement.domain.campaignsalechannel.model.CampaignSaleChannelType;

import java.util.List;

public interface CampaignSaleChannelTypeRepository {

    List<CampaignSaleChannelType> findAllCampaignSaleChannelTypes();

}
