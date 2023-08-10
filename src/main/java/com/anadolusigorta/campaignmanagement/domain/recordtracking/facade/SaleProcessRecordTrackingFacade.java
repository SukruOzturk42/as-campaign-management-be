package com.anadolusigorta.campaignmanagement.domain.recordtracking.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignCriteria;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.recordtracking.model.SaleProcessCriteria;
import com.anadolusigorta.campaignmanagement.domain.recordtracking.port.SalesProcessRepository;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleOperationsRequests;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository.SaleFindCampaignsRequestJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SaleProcessRecordTrackingFacade {

    private final SalesProcessRepository salesProcessRepository;

    public PageContent<SaleOperationsRequests> getFindRequestsPageable(SaleProcessCriteria saleProcessCriteria, Pageable pageable) {
        return salesProcessRepository.findAllSaleOperationsRequestPageable(saleProcessCriteria, pageable);
    }

    public PageContent<SaleOperationsRequests> getCheckPageable(SaleProcessCriteria saleProcessCriteria, Pageable pageable) {
        return salesProcessRepository.findAllCheckRequestPageable(saleProcessCriteria, pageable);
    }

    public PageContent<SaleOperationsRequests> getNotifyPageable(SaleProcessCriteria saleProcessCriteria, Pageable pageable) {
        return salesProcessRepository.findAllNotifyRequestPageable(saleProcessCriteria, pageable);
    }
}
