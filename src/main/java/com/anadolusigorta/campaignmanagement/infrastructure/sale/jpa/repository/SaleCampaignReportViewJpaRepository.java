package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.common.jpa.ReadOnlyRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SaleCampaignReportViewEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SaleTransactionalDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SaleCampaignReportViewJpaRepository extends ReadOnlyRepository<SaleCampaignReportViewEntity, Long> {

    Page<SaleCampaignReportViewEntity> findAllByCampaignNameContains(String campaignName,Pageable pageable);

}
