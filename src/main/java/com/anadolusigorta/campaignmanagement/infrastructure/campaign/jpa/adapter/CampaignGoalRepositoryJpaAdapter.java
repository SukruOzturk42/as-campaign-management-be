package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CampaignGoal;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignGoalRepository;
import com.anadolusigorta.campaignmanagement.domain.campaigngoal.model.CreateCampaignGoal;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignGoalEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignGoalJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.jpa.repository.CampaignGoalTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigngoal.jpa.repository.CampaignPolicyTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignsalechannel.jpa.repository.CampaignSaleChannelTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.repository.ReferenceTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CampaignGoalRepositoryJpaAdapter implements CampaignGoalRepository {

    private final CampaignGoalJpaRepository campaignGoalJpaRepository;
    private final CampaignGoalTypeJpaRepository campaignGoalTypeJpaRepository;
    private final CampaignSaleChannelTypeJpaRepository campaignSaleChannelTypeJpaRepository;
    private final CampaignPolicyTypeJpaRepository campaignPolicyTypeJpaRepository;
    private final CampaignJpaRepository campaignJpaRepository;
    private final ReferenceTypeJpaRepository referenceTypeJpaRepository;

    @Override
    public List<CampaignGoal> getCampaignGoalsByCampaignId(Long campaignId) {
        return campaignGoalJpaRepository
                .findAllByCampaignId(campaignId).stream().map(CampaignGoalEntity::toModel).collect(Collectors.toList());
    }

    @Override
    public List<CampaignGoal> saveOrUpdateCampaignGoal(CreateCampaignGoal campaignGoal) {
        CampaignGoalEntity entity;
        if(campaignGoal.getId() != null) {
            entity = campaignGoalJpaRepository.getOne(campaignGoal.getId());
            entity.setAmountGoal(campaignGoal.getAmountGoal());
            entity.setPolicyGoal(campaignGoal.getPolicyGoal());
            entity.setInsuredGoal(campaignGoal.getInsuredGoal());
        } else {
            entity = new CampaignGoalEntity();
            var goalType = campaignGoalTypeJpaRepository.getOne(campaignGoal.getGoalTypeId());
            if(campaignGoal.getReferenceTypeId() != null) {
                var referenceType=referenceTypeJpaRepository.findById(campaignGoal.getReferenceTypeId())
                        .orElseThrow(()->new CampaignManagementException("reference.type.not.found"));
                entity.setReferenceType(referenceType);
            }
            entity.setGoalType(goalType);
            entity.setAmountGoal(campaignGoal.getAmountGoal());
            entity.setPolicyGoal(campaignGoal.getPolicyGoal());
            entity.setCampaign(campaignJpaRepository.getOne(campaignGoal.getCampaignId()));
            entity.setInsuredGoal(campaignGoal.getInsuredGoal());
        }
        campaignGoalJpaRepository.save(entity);
        return campaignGoalJpaRepository.findAllByCampaignId(campaignGoal.getCampaignId())
                .stream().map(CampaignGoalEntity::toModel).collect(Collectors.toList());
    }

    @Override
    public List<CampaignGoal> deleteCampaignGoalByCampaignGoal(CampaignGoal campaignGoal) {
        var deletedGoal = campaignGoalJpaRepository.findById(campaignGoal.getId());
        if(deletedGoal.isPresent()){
            deletedGoal.get().setIsActive(Boolean.FALSE);
            campaignGoalJpaRepository.save(deletedGoal.get());
        }

        return campaignGoalJpaRepository.findAllByCampaignId(campaignGoal.getCampaignId())
                .stream().map(CampaignGoalEntity::toModel).collect(Collectors.toList());
    }

}
