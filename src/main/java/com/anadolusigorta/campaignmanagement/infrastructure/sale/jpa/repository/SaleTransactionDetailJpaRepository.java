package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SaleTransactionalDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SaleTransactionDetailJpaRepository extends JpaRepository<SaleTransactionalDetailEntity, Long> {

	Optional<SaleTransactionalDetailEntity> findByTransactionId(String transactionId);


}
