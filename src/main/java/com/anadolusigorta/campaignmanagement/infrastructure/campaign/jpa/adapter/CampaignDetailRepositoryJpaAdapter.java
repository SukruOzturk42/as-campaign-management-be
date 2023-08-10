package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignDetail;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignDetailRepository;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.user.facade.UserSecurityFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignDetailEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignDetailJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.repository.ReferenceTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CampaignDetailRepositoryJpaAdapter implements CampaignDetailRepository {

	private final CampaignDetailJpaRepository campaignDetailJpaRepository;
	private final CampaignJpaRepository campaignJpaRepository;
	private final UserSecurityFacade userSecurityFacade;
	private final ReferenceTypeJpaRepository referenceTypeJpaRepository;


	@Override
	public List<CampaignDetail> getCampaignDetailsByCampaignId(Long campaignId) {
		return campaignDetailJpaRepository.findAllByCampaignId(campaignId).stream().map(CampaignDetailEntity::toModel)
				.collect(Collectors.toList());
	}

	@Override
	public List<CampaignDetail> getCampaignDetailsByCampaignIdAndSalesChannel(Long campaignId,
			String salesChannelType) {

		List<CampaignDetailEntity> detailEntity;
		if(salesChannelType!=null && !salesChannelType.equals("")){
			detailEntity = campaignDetailJpaRepository
					.findAllByCampaignIdAndReferenceTypeName(campaignId, salesChannelType);
		}
		else {
			detailEntity = campaignDetailJpaRepository
					.findAllByCampaignId(campaignId);
		}
		return detailEntity.stream()
				.map(CampaignDetailEntity::toModel)
				.toList();

	}

	@Override
	public List<CampaignDetail> saveOrUpdateCampaignDetail(CampaignDetail campaignDetail) {
		CampaignDetailEntity entity;
		if (campaignDetail.getId() != null) {
			entity = campaignDetailJpaRepository.getOne(campaignDetail.getId());
			entity.setReferenceType(referenceTypeJpaRepository.findById(campaignDetail.getReferenceTypeId())
					.orElseThrow(()->new CampaignManagementException("reference.type.not.found")));
			entity.setCampaignLink(campaignDetail.getCampaignLink());
			entity.setCampaignText(campaignDetail.getCampaignText());
		} else {
			entity = new CampaignDetailEntity();
			entity.setReferenceType(referenceTypeJpaRepository.findById(campaignDetail.getReferenceTypeId())
					.orElseThrow(()->new CampaignManagementException("reference.type.not.found")));
			entity.setCampaignLink(campaignDetail.getCampaignLink());
			entity.setCampaign(campaignJpaRepository.getOne(campaignDetail.getCampaignId()));
			entity.setCampaignText(campaignDetail.getCampaignText());
		}
		campaignDetailJpaRepository.save(entity);

		return campaignDetailJpaRepository.findAllByCampaignId(campaignDetail.getCampaignId()).stream()
				.map(CampaignDetailEntity::toModel).collect(Collectors.toList());
	}

	@Override
	public List<CampaignDetail> deleteCampaignDetailByCampaignDetail(CampaignDetail campaignDetail) {
		var deletedDetaildetail = campaignDetailJpaRepository.findById(campaignDetail.getId());
		if(deletedDetaildetail.isPresent()){
			deletedDetaildetail.get().setIsActive(Boolean.FALSE);
			campaignDetailJpaRepository.save(deletedDetaildetail.get());
		}
		return campaignDetailJpaRepository.findAllByCampaignId(campaignDetail.getCampaignId()).stream()
				.map(CampaignDetailEntity::toModel).collect(Collectors.toList());
	}



}
