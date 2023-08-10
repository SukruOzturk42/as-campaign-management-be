package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignReason;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignReasonRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignReasonEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignReasonJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CampaignReasonRepositoryJpaAdapter implements CampaignReasonRepository {

    private final CampaignReasonJpaRepository campaignReasonJpaRepository;
    private final CampaignJpaRepository campaignJpaRepository;

    @Override
    public List<CampaignReason> getCampaignReasonsByCampaignId(Long campaignId) {
        return campaignReasonJpaRepository.findAllByCampaignId(campaignId).stream()
                .map(CampaignReasonEntity::toModel).collect(Collectors.toList());
    }

    @Override
    public List<CampaignReason> saveOrUpdateCampaignReason(CampaignReason campaignReason) {
        CampaignReasonEntity entity;
        if(campaignReason != null && campaignReason.getDecisionDescription() != null && campaignReason.getDecisionDescription().isBlank()){
            campaignReason.setDecisionDescription(null);
        }
        if(campaignReason.getId() != null) {
            entity = campaignReasonJpaRepository.getOne(campaignReason.getId());
            entity.setDecidingOrganization(campaignReason.getDecidingOrganization());
            entity.setDecisionDescription(campaignReason.getDecisionDescription());
            entity.setDecisionDate(campaignReason.getDecisionDate());
            entity.setDecisionNumber(campaignReason.getDecisionNumber());
        } else {
            entity = new CampaignReasonEntity();
            entity.setDecidingOrganization(campaignReason.getDecidingOrganization());
            entity.setDecisionDescription(campaignReason.getDecisionDescription());
            entity.setDecisionDate(campaignReason.getDecisionDate());
            entity.setDecisionNumber(campaignReason.getDecisionNumber());
            entity.setCampaign(campaignJpaRepository.getOne(campaignReason.getCampaignId()));
        }
        campaignReasonJpaRepository.save(entity);
        return getCampaignReasonsByCampaignId(campaignReason.getCampaignId());
    }

    @Override
    public List<CampaignReason> deleteCampaignReasonByCampaignReason(CampaignReason campaignReason) {
        var reason = campaignReasonJpaRepository.findById(campaignReason.getId());
        if(reason.isPresent()){
            reason.get().setIsActive(Boolean.FALSE);
            campaignReasonJpaRepository.save(reason.get());
        }
        return getCampaignReasonsByCampaignId(campaignReason.getCampaignId());
    }
}
