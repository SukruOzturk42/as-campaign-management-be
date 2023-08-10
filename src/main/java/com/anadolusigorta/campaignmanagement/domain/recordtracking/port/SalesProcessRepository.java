package com.anadolusigorta.campaignmanagement.domain.recordtracking.port;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignCriteria;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.recordtracking.model.SaleProcessCriteria;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleOperationsRequests;
import org.springframework.data.domain.Pageable;

public interface SalesProcessRepository {


    PageContent<SaleOperationsRequests> findAllSaleOperationsRequestPageable(SaleProcessCriteria saleProcessCriteria, Pageable pageable);

    PageContent<SaleOperationsRequests> findAllCheckRequestPageable(SaleProcessCriteria saleProcessCriteria, Pageable pageable);

    PageContent<SaleOperationsRequests> findAllNotifyRequestPageable(SaleProcessCriteria saleProcessCriteria, Pageable pageable);

    }
