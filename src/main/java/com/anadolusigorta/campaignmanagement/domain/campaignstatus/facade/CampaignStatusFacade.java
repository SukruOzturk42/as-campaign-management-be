package com.anadolusigorta.campaignmanagement.domain.campaignstatus.facade;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.ApprovalCampaignCriteria;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignCriteria;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignVersion;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.*;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.port.CampaignApprovalStatusRepository;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.port.CampaignSearchRepository;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.port.CampaignStatusRepository;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.handler.CampaignRuleGroupHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignStatusFacade {

    private final CampaignStatusRepository campaignStatusRepository;
    private final CampaignApprovalStatusRepository campaignApprovalStatusRepository;
    private final CampaignSearchRepository campaignSearchRepository;

    private final CampaignRuleGroupHandler campaignRuleGroupHandler;
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CampaignStatus> getCampaignStatuses() {
        return campaignStatusRepository.getCampaignStatuses();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CampaignApprovalStatus> getApprovalStatuses() {
        return campaignApprovalStatusRepository.getApprovalStatuses();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CampaignSearchStatusEnum> getCampaignSearchStatus() {
        return Arrays.asList(CampaignSearchStatusEnum.values());
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CampaignSearchApprovalEnum> getCampaignSearchApproval() {
        return Arrays.asList(CampaignSearchApprovalEnum.values());
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CampaignInformation> getApprovalCampaignsInformation(String campaignStatusType, Long campaignTypeId) {
        return campaignSearchRepository.getContactCampaignsInformation(campaignStatusType,campaignTypeId);
    }

    public PageContent<CampaignInformation> getPageableApprovalCampaignsInformation(CampaignCriteria approvalCampaignCriteria, Pageable pageable) {
        var campaignsPageable=campaignSearchRepository
                .findContactCampaignsVersionPageable(approvalCampaignCriteria, pageable);


        return PageContent.<CampaignInformation>builder()
                .content(campaignsPageable.getContent()
                        .stream()
                        .map(CampaignVersion::getCampaignInformation)
                        .collect(Collectors.toList()))
                .size(pageable.getPageSize())
                .page(pageable.getPageNumber())
                .totalItems(campaignsPageable.getTotalItems())
                .totalPages(campaignsPageable.getTotalPages())
                .build();
    }
}
