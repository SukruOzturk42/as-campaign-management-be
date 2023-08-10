package com.anadolusigorta.campaignmanagement.domain.sale.port;

import com.anadolusigorta.campaignmanagement.domain.sale.model.CheckStatusFlow;

import java.util.List;

public interface SaleCheckFlowRepository {

    List<CheckStatusFlow> findAllByCampaignTypeAndRequestTypeOrderByRunOrder(String campaignType,String requestType);
}
