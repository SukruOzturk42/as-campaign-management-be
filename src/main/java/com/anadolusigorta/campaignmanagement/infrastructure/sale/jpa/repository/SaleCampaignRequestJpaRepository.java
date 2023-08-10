package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository;

import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionOperationType;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.FindSaleCampaignsRequestEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SaleCampaignRequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SaleCampaignRequestJpaRepository extends JpaRepository<SaleCampaignRequestEntity, Long>, JpaSpecificationExecutor<SaleCampaignRequestEntity> {

    List<SaleCampaignRequestEntity> findByTransactionalIdAndRequestType(String transactionId, SaleTransactionOperationType requestType);




}
