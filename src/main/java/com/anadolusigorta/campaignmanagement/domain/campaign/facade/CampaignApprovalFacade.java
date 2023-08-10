package com.anadolusigorta.campaignmanagement.domain.campaign.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignApproval;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignApprovalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignApprovalFacade {

    private final CampaignApprovalRepository campaignApprovalRepository;

    public List<CampaignApproval> getCampaignApprovalHistoryByCampaignId(Long campaignId) {
        return campaignApprovalRepository.getCampaignApprovalHistoryByCampaignId(campaignId);
    }


}
