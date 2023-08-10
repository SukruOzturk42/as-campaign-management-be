package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignApproval;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateCampaign;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignApprovalRepository;
import com.anadolusigorta.campaignmanagement.domain.user.facade.UserSecurityFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignStateHistory;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignVersionEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignStateHistoryJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity.RoleAuthorizationActionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignStateHistoryJpaRepositoryAdapter implements CampaignApprovalRepository {

    private final CampaignStateHistoryJpaRepository campaignStateHistoryJpaRepository;
    private final UserSecurityFacade userSecurityFacade;

    @Override
    public List<CampaignApproval> getCampaignApprovalHistoryByCampaignId(Long campaignId) {
        return campaignStateHistoryJpaRepository.findAllByCampaignId(campaignId).stream()
                .map(CampaignStateHistory::toModel).collect(Collectors.toList());
    }

    public void saveCampaignStatusHistory(CampaignEntity campaignEntity, CampaignVersionEntity campaignVersionEntity
            ,CreateCampaign createCampaign, RoleAuthorizationActionEntity roleAuthorizationActionEntity) {

        if(createCampaign.getCampaignInformation().getActionId()!=null){
            var statusHistoryEntity=new CampaignStateHistory();
            statusHistoryEntity.setCampaign(campaignEntity);
            statusHistoryEntity.setRoleAuthorizationAction(roleAuthorizationActionEntity);
            statusHistoryEntity.setCampaignVersion(campaignVersionEntity);
            statusHistoryEntity.setUsername(userSecurityFacade.getActiveUser().getUsername());
            campaignStateHistoryJpaRepository.save(statusHistoryEntity);
        }

    }
}
