package com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignApprovalStatus;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignApprovalStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.port.CampaignApprovalStatusRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.entity.CampaignApprovalStatusEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.repository.CampaignApprovalStatusJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignApprovalStatusJpaAdapter implements CampaignApprovalStatusRepository {

    private final CampaignApprovalStatusJpaRepository campaignApprovalStatusJpaRepository;


    @Override
    public List<CampaignApprovalStatus> getApprovalStatuses() {
        return campaignApprovalStatusJpaRepository.findAll().stream()
                .map(CampaignApprovalStatusEntity::toModel).collect(Collectors.toList());
    }
}
