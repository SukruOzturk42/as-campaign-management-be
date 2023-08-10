package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionDetail;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.port.SaleTransactionDetailRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SaleTransactionalDetailEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository.SaleTransactionDetailJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SaleTransactionDetailDetailJpaAdapter implements SaleTransactionDetailRepository {

	private final SaleTransactionDetailJpaRepository saleTransactionDetailJpaRepository;

	@Override
	public SaleTransactionDetail findByTransactionId(String transactionId,String contactNo) {
		return saleTransactionDetailJpaRepository.findByTransactionId(transactionId)
				.orElseThrow(()->new CampaignManagementException("transaction.not.found",contactNo))
				.toModel();
	}

	@Override
	public SaleTransactionDetail save(SaleTransactionDetail saleTransactionDetail) {
		var entity= SaleTransactionalDetailEntity.fromModel(saleTransactionDetail);
		return saleTransactionDetailJpaRepository.save(entity).toModel();

	}
}
