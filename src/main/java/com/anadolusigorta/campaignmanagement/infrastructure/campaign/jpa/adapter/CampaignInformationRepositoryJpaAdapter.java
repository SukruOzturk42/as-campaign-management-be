/* odeon_sukruo created on 11.05.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateCampaign;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignApprovalStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.user.facade.UserSecurityFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignVersionEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.*;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.entity.CampaignStatusEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.repository.CampaignStatusJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity.RoleAuthorizationActionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignInformationRepositoryJpaAdapter {

	private final CampaignTypeJpaRepository campaignTypeJpaRepository;
	private final CampaignGroupJpaRepository campaignGroupJpaRepository;
	private final CampaignInformationJpaRepository campaignInformationJpaRepository;
	private final CampaignVersionRepositoryJpaAdapter campaignVersionRepositoryJpaAdapter;
	private final UserSecurityFacade userSecurityFacade;
	private final CampaignStatusJpaRepository campaignStatusJpaRepository;

	private final CustomerCampaignJpaRepository customerCampaignJpaRepository;

	public CampaignInformationEntity save(CreateCampaign createCampaign,CampaignEntity campaignEntity,
			CampaignVersionEntity campaignVersionEntity,RoleAuthorizationActionEntity action){
         var cmInfo=toEntity(createCampaign,campaignEntity,campaignVersionEntity,action);
		return campaignInformationJpaRepository.save(cmInfo);
	}
	public CampaignInformationEntity save(CampaignInformationEntity campaignInformationEntity){
		return campaignInformationJpaRepository.save(campaignInformationEntity);
	}

	private CampaignInformationEntity toEntity(CreateCampaign createCampaign,CampaignEntity campaignEntity,
			CampaignVersionEntity campaignVersionEntity,RoleAuthorizationActionEntity roleAuthorizationAction) {
		return CampaignInformationEntity.builder()
				.campaign(campaignEntity)
				.campaignVersion(campaignVersionEntity)
				.campaignGroup(createCampaign.getCampaignInformation().getCampaignGroupId()!=null?
						campaignGroupJpaRepository.findById(createCampaign.getCampaignInformation().getCampaignGroupId())
						.orElseThrow(()->new CampaignManagementException("campaign.group.not.found")):null)
				.campaignName(createCampaign.getCampaignInformation().getCampaignName())
				.campaignTitle(createCampaign.getCampaignInformation().getCampaignTitle())
				.campaignType(campaignTypeJpaRepository.findById(createCampaign.getCampaignInformation().getCampaignTypeId())
						.orElseThrow(()->new CampaignManagementException("campaign.type.not.found")))
				.campaignStatus(getCampaignStatus(roleAuthorizationAction,createCampaign))
				.campaignApprovalStatus(roleAuthorizationAction.getCampaignNextApprovalStatus())
				.tags(((createCampaign.getCampaignInformation().getTags() != null) && !createCampaign
						.getCampaignInformation().getTags().isEmpty()) ?
						String.join(Constants.TAGS_DELIMITER, createCampaign.getCampaignInformation().getTags()) :
						null)
				.campaignStartDate(createCampaign.getCampaignInformation().getCampaignStartDate())
				.campaignEndDate(createCampaign.getCampaignInformation().getCampaignEndDate())
				.versionStartDate(createCampaign.getCampaignInformation().getVersionStartDate())
				.campaignOwner(userSecurityFacade.getUserFullName())
				.roleAuthorizationAction(roleAuthorizationAction)
				.actionDescription(createCampaign.getCampaignInformation().getActionDescription())
				.shortDescription(createCampaign.getCampaignInformation().getShortDescription())
				.hasCustomerLimit(createCampaign.getCampaignInformation().getHasCustomerLimit())
				.customerLimitSize(createCampaign.getCampaignInformation().getCustomerLimitSize()==null?
						Constants.DEFAULT_CUSTOMER_LIMIT_SIZE:
						createCampaign.getCampaignInformation().getCustomerLimitSize())
				.isStartedRewardSend(Boolean.TRUE)
				.isTriggeredRewardSend(Boolean.FALSE)
				.build();
	}

	public CampaignInformationEntity findByCampaignId(Long campaignId){
		var version =campaignVersionRepositoryJpaAdapter.findActiveVersionByCampaignId(campaignId);
		return campaignInformationJpaRepository.findByCampaignVersionId(version.getId());

	}

	public CampaignInformationEntity findByCampaignIdAndVersionId(Long campaignId,Long versionId){
		var versionEntity =campaignVersionRepositoryJpaAdapter.findVersionByCampaignIdAndVersionId(campaignId,versionId);
		return campaignInformationJpaRepository.findByCampaignVersionId(versionEntity.getId());

	}

	public List<CampaignInformation> findAllByCampaignId(Long campaignId){
		return campaignInformationJpaRepository.findByCampaignId(campaignId).stream()
				.map(CampaignInformationEntity::toModel)
				.collect(Collectors.toList());

	}

	public List<CampaignInformation> findAllByResendActive(){
		return campaignInformationJpaRepository.findByIsStartedRewardSendTrueAndIsTriggeredRewardSendTrue().stream()
				.map(CampaignInformationEntity::toModel)
				.collect(Collectors.toList());

	}




	private CampaignInformationEntity validate(CampaignInformationEntity campaignInformation){
		var startDate=campaignInformation.getCampaignStartDate();
		var endDate=campaignInformation.getCampaignEndDate();
		var status=campaignInformation.getCampaignApprovalStatus();
		if(!status.getCampaignApprovalStatus().equals(CampaignApprovalStatusEnum.APPROVED_CAMPAIGN)){
			if(startDate.isBefore(LocalDateTime.now())){
				throw new CampaignManagementException("cm.constraint.campaign.start.date.FutureOrPresent.message");
			}

		}
		return campaignInformation;
	}

	private CampaignStatusEntity getCampaignStatus(RoleAuthorizationActionEntity roleAuthorizationActionEntity, CreateCampaign createCampaign){

		var campaignInformation = createCampaign.getCampaignInformation();
		var nextStatus = roleAuthorizationActionEntity.getCampaignNextStatus();

		if(nextStatus.getCampaignStatus().equals(CampaignStatusEnum.ACTIVE_CAMPAIGN)
				&& (campaignInformation.getVersionStartDate() != null
				&& campaignInformation.getVersionStartDate().isAfter(LocalDateTime.now()))){

			return campaignStatusJpaRepository.findByCampaignStatus(CampaignStatusEnum.PENDING_VERSION_START_DATE)
					.orElseThrow(() -> new CampaignManagementException("campaign.status.not.found"));
		}

			return nextStatus;
	}
}
