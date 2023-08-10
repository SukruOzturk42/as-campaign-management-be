package com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaignbudget.model.CampaignBudget;
import com.anadolusigorta.campaignmanagement.domain.campaignbudget.model.CampaignBudgetDetail;
import com.anadolusigorta.campaignmanagement.domain.campaignbudget.model.CreateCampaignBudget;
import com.anadolusigorta.campaignmanagement.domain.campaignbudget.port.CampaignBudgetRepository;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.model.Status;
import com.anadolusigorta.campaignmanagement.domain.user.facade.UserSecurityFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.budgetitemtype.jpa.repository.BudgetItemTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.jpa.entity.CampaignBudgetDetailEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.jpa.entity.CampaignBudgetEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.jpa.repository.CampaignBudgetDetailJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignbudget.jpa.repository.CampaignBudgetJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignBudgetRepositoryJpaAdapter implements CampaignBudgetRepository {

    private final CampaignBudgetJpaRepository campaignBudgetJpaRepository;
    private final CampaignJpaRepository campaignJpaRepository;
    private final BudgetItemTypeJpaRepository budgetItemTypeJpaRepository;
    private final CampaignBudgetDetailJpaRepository campaignBudgetDetailJpaRepository;
    private final UserSecurityFacade userSecurityFacade;

    @Override
    @Transactional
    public CampaignBudget saveCampaignBudget(CreateCampaignBudget createCampaignBudget) {
        return campaignBudgetJpaRepository.save(toEntity(createCampaignBudget)).toModel();
    }

    @Override
    public List<CampaignBudget> getAllCampaignBudgets(Long campaignId) {
        var budgetList = campaignBudgetJpaRepository.findAllByCampaignId(campaignId);
        if (budgetList.isEmpty()) {
            return new ArrayList<>();
        } else {
            return budgetList.get().stream().map(CampaignBudgetEntity::toModel).collect(Collectors.toList());
        }
    }

    @Override
    public void deleteCampaignBudget(Long campaignId) {
        var budget = campaignBudgetJpaRepository.findById(campaignId);
        if (budget.isPresent()) {
            budget.get().setIsActive(Boolean.FALSE);
            campaignBudgetJpaRepository.save(budget.get());
        }
    }

    private CampaignBudgetEntity toEntity(CreateCampaignBudget createCampaignBudget) {
        var campaignBudgetEntity = new CampaignBudgetEntity();

        campaignBudgetEntity.setId(createCampaignBudget.getId());
        var budgetTypeEntity = budgetItemTypeJpaRepository.findById(createCampaignBudget.getBudgetItemId())
                .orElseThrow(() -> new CampaignManagementException("budget.type.not.found"));
        campaignBudgetEntity.setBudgetDate(createCampaignBudget.getBudgetDate());
        campaignBudgetEntity.setBudgetItem(budgetTypeEntity);
        campaignBudgetEntity.setBudgetAmount(createCampaignBudget.getBudgetAmount());
        var campaign = campaignJpaRepository.findById(createCampaignBudget.getCampaignId())
                .orElseThrow(() -> new CampaignManagementException("campaign.not.found"));
        campaignBudgetEntity.setCampaign(campaign);
        if (createCampaignBudget.getId() != null) {
            var existCampaignBudgetEntity = campaignBudgetJpaRepository.findById(createCampaignBudget.getId())
                    .orElseThrow(() -> new CampaignManagementException("budget.not.found"));
            campaignBudgetEntity.setCreatedAt(existCampaignBudgetEntity.getCreatedAt());
            createCampaignBudgetDetail(campaignBudgetEntity, existCampaignBudgetEntity);
        }else{
            campaignBudgetEntity.setCreatedAt(LocalDateTime.now());
            createCampaignBudgetDetailForSave(campaignBudgetEntity);
        }

        return campaignBudgetEntity;
    }


    private void createCampaignBudgetDetail(CampaignBudgetEntity newCampaignBudgetEntity, CampaignBudgetEntity oldCampaignBudgetEntity) {
        var user = userSecurityFacade.getActiveUser();
        if (newCampaignBudgetEntity.getBudgetDate().compareTo(oldCampaignBudgetEntity.getBudgetDate()) != 0) {
            var campaignBudgetDetailEntity = new CampaignBudgetDetailEntity();
            campaignBudgetDetailEntity.setUpdater(user.getFullName());
            campaignBudgetDetailEntity.setCampaignBudget(newCampaignBudgetEntity);
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            campaignBudgetDetailEntity.setDescription("Bütçe Tarihi " + newCampaignBudgetEntity.getBudgetDate().format(format) + " olarak güncellendi");
            newCampaignBudgetEntity.getCampaignBudgeDetailtList().add(campaignBudgetDetailEntity);
        }
        if (!newCampaignBudgetEntity.getBudgetAmount().equals(oldCampaignBudgetEntity.getBudgetAmount())) {
            var campaignBudgetDetailEntity = new CampaignBudgetDetailEntity();
            campaignBudgetDetailEntity.setUpdater(user.getFullName());
            campaignBudgetDetailEntity.setCampaignBudget(newCampaignBudgetEntity);
            campaignBudgetDetailEntity.setDescription("Bütçe " + formatPrice(newCampaignBudgetEntity.getBudgetAmount()) + " olarak güncellendi");
            newCampaignBudgetEntity.getCampaignBudgeDetailtList().add(campaignBudgetDetailEntity);
        }
    }

    private void createCampaignBudgetDetailForSave(CampaignBudgetEntity newCampaignBudgetEntity) {
        var user = userSecurityFacade.getActiveUser();
        var campaignBudgetDetailEntity = new CampaignBudgetDetailEntity();
        campaignBudgetDetailEntity.setUpdater(user.getFullName());
        campaignBudgetDetailEntity.setCampaignBudget(newCampaignBudgetEntity);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        campaignBudgetDetailEntity.setDescription(
                "Bütçe Tarihi " + newCampaignBudgetEntity.getBudgetDate().format(format) + " olarak eklendi." +
                " Bütçe " + formatPrice(newCampaignBudgetEntity.getBudgetAmount()) + " olarak eklendi.");
        newCampaignBudgetEntity.getCampaignBudgeDetailtList().add(campaignBudgetDetailEntity);
    }

    private String formatPrice(Long price) {
        Locale tr = new Locale("tr", "TR");
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(tr);
        return dollarFormat.format(price);
    }

}
