package com.anadolusigorta.campaignmanagement.domain.budgetitemtype.port;

import com.anadolusigorta.campaignmanagement.domain.budgetitemtype.model.BudgetITemType;

import java.util.List;

public interface BudgetITemTypeRepository {
    List<BudgetITemType> getAllBudgetTypes();
}
