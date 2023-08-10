package com.anadolusigorta.campaignmanagement.infrastructure.budgetitemtype.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.budgetitemtype.jpa.entity.BudgetItemTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetItemTypeJpaRepository  extends JpaRepository<BudgetItemTypeEntity, Long> {
}
