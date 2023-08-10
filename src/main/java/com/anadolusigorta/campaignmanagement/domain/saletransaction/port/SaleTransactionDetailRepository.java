package com.anadolusigorta.campaignmanagement.domain.saletransaction.port;

import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionDetail;

public interface SaleTransactionDetailRepository {

	SaleTransactionDetail findByTransactionId(String transactionId,String contactNo);

	SaleTransactionDetail save(SaleTransactionDetail saleTransactionDetail);

}
