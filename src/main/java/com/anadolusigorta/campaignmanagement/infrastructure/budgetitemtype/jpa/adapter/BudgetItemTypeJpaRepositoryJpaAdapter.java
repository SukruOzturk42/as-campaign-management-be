package com.anadolusigorta.campaignmanagement.infrastructure.budgetitemtype.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.budgetitemtype.model.BudgetITemType;
import com.anadolusigorta.campaignmanagement.domain.budgetitemtype.port.BudgetITemTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.budgetitemtype.jpa.entity.BudgetItemTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.budgetitemtype.jpa.repository.BudgetItemTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetItemTypeJpaRepositoryJpaAdapter implements BudgetITemTypeRepository {

    private final BudgetItemTypeJpaRepository budgetItemTypeJpaRepository;
    @Override
    public List<BudgetITemType> getAllBudgetTypes() {
        var budgetItemTypes = budgetItemTypeJpaRepository.findAll();
        return budgetItemTypes.stream().map(this::toDto).collect(Collectors.toList());
    }

    private BudgetITemType toDto(BudgetItemTypeEntity budgetItemTypeEntity){
        return BudgetITemType.builder()
                .id(budgetItemTypeEntity.getId())
                .description(budgetItemTypeEntity.getDescription())
                .name(budgetItemTypeEntity.getName())
                .build();
    }
}
