package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository;

import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionOperationType;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.NotifyTempEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SaleCampaignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface NotifyTempJpaRepository
		extends JpaRepository<NotifyTempEntity, Long> {

	List<NotifyTempEntity> findByRequestContains(String contains);
}
