package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignGroup;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignGroupRepository;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignGroupEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignGroupJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignGroupRepositoryJpaAdapter implements CampaignGroupRepository {
    private final CampaignGroupJpaRepository campaignGroupJpaRepository;


    @Override
    public List<CampaignGroup> getCampaignGroups() {
        return campaignGroupJpaRepository.findAll().stream().map(CampaignGroupEntity::toModel).collect(Collectors.toList());
    }

	@Override
	public CampaignGroup createCampaignGroup(CampaignGroup campaignGroup) {

		var existingCampaignGroups = getCampaignGroups();
		if(existingCampaignGroups.stream().anyMatch(item->item.getName().equals(campaignGroup.getName()))){
			throw new CampaignManagementException(ExceptionConstants.EXISTING_CAMPAIGN_GROUP);
		}else{
			var campaignGroupEntity=new CampaignGroupEntity();
			campaignGroupEntity.setName(campaignGroup.getName());
			campaignGroupEntity.setDescription(campaignGroup.getName());
			return campaignGroupJpaRepository.save(campaignGroupEntity).toModel();
		}
	}
}
