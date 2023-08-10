package com.anadolusigorta.campaignmanagement.domain.budgetitemtype.facade;


import com.anadolusigorta.campaignmanagement.domain.budgetitemtype.model.BudgetITemType;
import com.anadolusigorta.campaignmanagement.domain.budgetitemtype.port.BudgetITemTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetITemTypesFacade {

    private final BudgetITemTypeRepository budgetITemTypeRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<BudgetITemType> getAllBudgetTypes() {
        return budgetITemTypeRepository.getAllBudgetTypes();
    }
}
