package com.anadolusigorta.campaignmanagement.domain.sale.port;

import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.sale.model.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SaleCampaignCriteriaRepository {

	PageContent<SaleReport> findSaleCampaigns(SaleReportCriteria criteria, Pageable pageable);


	 List<SaleReport> findSaleCampaignInformation(SaleReportCriteria saleCampaignInformationCriteria);
}
