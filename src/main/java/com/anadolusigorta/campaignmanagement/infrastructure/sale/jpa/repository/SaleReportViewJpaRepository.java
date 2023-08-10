package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.common.jpa.ReadOnlyRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SaleCampaignReportViewEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SaleReportViewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SaleReportViewJpaRepository extends ReadOnlyRepository<SaleReportViewEntity, Long> {

    List<SaleReportViewEntity> findByCampaignIdAndRequestType(Long campaignId,String requestType);



}
