package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.action.model.ActionTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.*;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignRepository;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.user.facade.UserSecurityFacade;
import com.anadolusigorta.campaignmanagement.domain.user.model.RoleAuthorizationAction;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignRuleGroupEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignVersionEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CustomerCampaignEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.*;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.adapter.RoleAuthorizationActionJpaAdapter;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity.RoleAuthorizationActionEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.repository.RoleAuthorizationActionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
@Transactional
public class CampaignRepositoryJpaAdapter implements CampaignRepository {


    private final CampaignJpaRepository campaignJpaRepository;
    private final CampaignInformationRepositoryJpaAdapter campaignInformationRepositoryJpaAdapter;
    private final CampaignRuleGroupRepositoryJpaAdapter campaignRuleGroupRepositoryJpaAdapter;
    private final CampaignVersionRepositoryJpaAdapter campaignVersionRepositoryJpaAdapter;
    private final CampaignStateHistoryJpaRepositoryAdapter campaignStateHistoryJpaRepositoryAdapter;
	private final RoleAuthorizationActionJpaRepository roleAuthorizationActionJpaRepository;
	private final RoleAuthorizationActionJpaAdapter roleAuthorizationActionJpaAdapter;
	private final UserSecurityFacade userSecurityFacade;
	private final CustomerCampaignJpaRepository customerCampaignJpaRepository;


	@Override
	@Transactional
	public Campaign saveCampaign(CreateCampaign createCampaign) {

        var campaignEntity= saveCampaignEntity(createCampaign);
        var roleAction= getRoleAuthorization(createCampaign);
        var campaignVersion= campaignVersionRepositoryJpaAdapter.save(createCampaign,campaignEntity);
        var campaignInformation= campaignInformationRepositoryJpaAdapter
                .save(createCampaign,campaignEntity,campaignVersion,roleAction);

        var ruleGroups=createCampaign.getCampaignRuleGroups()
				.stream()
                .map(item->campaignRuleGroupRepositoryJpaAdapter
                        .save(createCampaign,campaignEntity,campaignVersion,item))
                .collect(Collectors.toSet());

		campaignStateHistoryJpaRepositoryAdapter.saveCampaignStatusHistory(campaignEntity,campaignVersion,createCampaign,
				campaignInformation.getRoleAuthorizationAction());


        campaignVersionRepositoryJpaAdapter
				.updateCampaignVersion(campaignVersion,campaignEntity,campaignInformation,ruleGroups);

        saveContactCampaign(campaignEntity,campaignVersion,roleAction);
        return Campaign.builder()
				.id(campaignEntity.getId())
				.campaignInformation(campaignInformation.toModel())
				.ruleGroups(ruleGroups.stream()
						.map(CampaignRuleGroupEntity::toModel)
						.sorted(Comparator.comparing(CampaignRuleGroup::getRuleGroupId))
						.collect(Collectors.toList()))
				.build();
    }


	@Override
	public CampaignInformation findCampaignInformationByCampaignId(Long id) {
		return campaignInformationRepositoryJpaAdapter
				.findByCampaignId(id).toModel();
	}

	@Override
	public CampaignInformation findCampaignInformationByCampaignIdAndVersionId(Long id, Long versionId) {
		return campaignInformationRepositoryJpaAdapter
				.findByCampaignIdAndVersionId(id,versionId)
				.toModel();
	}

	@Override
	public CampaignInformation findCampaignInformationByCampaignIdAndVersion(Long id, Long version) {
		var v=campaignVersionRepositoryJpaAdapter.findLatestVersionRecordByCampaignIdAndVersion(id,version);
		return findCampaignInformationByCampaignIdAndVersionId(id,v.getId());
	}

	@Override
	public List<CampaignInformation> findAllCampaignInformationByCampaignId(Long campaignId) {
		return campaignInformationRepositoryJpaAdapter.findAllByCampaignId(campaignId);
	}

	@Override
	public List<CampaignInformation> findAllCampaignInformationByResendActive() {
		return campaignInformationRepositoryJpaAdapter.findAllByResendActive();
	}


	@Override
	public List<CampaignVersion> getCampaignVersions(Long campaignId) {

       return campaignVersionRepositoryJpaAdapter.findCampaignVersions(campaignId);

	}

	@Override
	public Campaign findByCampaignIdAndCampaignVersionAndRuleGroupId(Long campaignId, Long campaignVersion,
			Long ruleGroupId) {

		var version=campaignVersionRepositoryJpaAdapter.findCampaignVersions(campaignId)
				.stream()
				.filter(v->v.getVersion().equals(campaignVersion))
				.findFirst()
				.orElseThrow(()->new CampaignManagementException("campaign.not.found"));
		var versionEntity=campaignVersionRepositoryJpaAdapter.findById(version.getId());
		var campaignInfo=campaignInformationRepositoryJpaAdapter.findByCampaignIdAndVersionId(campaignId,versionEntity.getId());
		var ruleGroups=versionEntity.getCampaignRuleGroups().stream()
				.filter(item->item.getId().equals(ruleGroupId))
				.map(CampaignRuleGroupEntity::toModel)
				.collect(Collectors.toList());
		return Campaign.builder()
				.id(campaignId)
				.version(campaignVersion)
				.campaignInformation(campaignInfo.toModel())
				.ruleGroups(ruleGroups)
				.build();

	}

	private CampaignEntity saveCampaignEntity(CreateCampaign createCampaign){
    	if(createCampaign.getId()!=null){
    		return campaignJpaRepository.findById(createCampaign.getId())
					.orElseThrow(()->new CampaignManagementException("campaign.not.found"));
		}else{
			CampaignEntity campaign=new CampaignEntity();
			return campaignJpaRepository.save(campaign);
		}

    }

	private RoleAuthorizationActionEntity getRoleAuthorization(CreateCampaign createCampaign){
		if(createCampaign.getId()==null){
			if(createCampaign.getCampaignInformation().getCreateMode().equals(CampaignCreateModeType.COPY_CAMPAIGN)){
				return roleAuthorizationActionJpaRepository
						.findFirstByUserRoleNameAndRoleActionName(userSecurityFacade.getUserRole().getName(),
								ActionTypeEnum.COPY.getValue())
						.orElseThrow(()->new CampaignManagementException(ExceptionConstants.ROLE_ACTION_NOT_FOUND));
			}
			return roleAuthorizationActionJpaRepository
					.findByUserRoleNameAndIsInitialActionTrue(userSecurityFacade.getUserRole().getName())
					.orElseThrow(()->new CampaignManagementException(ExceptionConstants.ROLE_ACTION_NOT_FOUND));
		}
		else{
			if(createCampaign.getCampaignInformation().getCreateMode().equals(CampaignCreateModeType.NEW_VERSION)){
				if(createCampaign.getActionId() != null){
					return roleAuthorizationActionJpaRepository.findById(createCampaign.getActionId()!=null?
									createCampaign.getActionId():
									createCampaign.getCampaignInformation().getActionId())
							.orElseThrow(()->new CampaignManagementException(ExceptionConstants.ROLE_ACTION_NOT_FOUND));
				}
				return roleAuthorizationActionJpaRepository
						.findFirstByUserRoleNameAndRoleActionName(userSecurityFacade.getUserRole().getName(),
								ActionTypeEnum.EDIT.getValue())
						.orElseThrow(()->new CampaignManagementException(ExceptionConstants.ROLE_ACTION_NOT_FOUND));
			}


			var roleAuths=roleAuthorizationActionJpaRepository
					.findAllByUserRoleNameAndCampaignStatusIdAndCampaignApprovalStatusIdAndType(userSecurityFacade.getUserRole().getName()
					,createCampaign.getCampaignInformation().getCampaignStatusId(),createCampaign.getCampaignInformation().getCampaignApprovalStatusId()
					,"action");

			var isContainsAction=createCampaign.getActionId()!=null?roleAuths
					.stream()
					.map(RoleAuthorizationActionEntity::getId)
					.toList()
					.contains(createCampaign.getActionId()):Boolean.TRUE;
			if(!isContainsAction){
				throw new CampaignManagementException(ExceptionConstants.ROLE_ACTION_NOT_FOUND);
			}


			return roleAuthorizationActionJpaRepository.findById(createCampaign.getActionId()!=null?
					createCampaign.getActionId():
					createCampaign.getCampaignInformation().getActionId())
					.orElseThrow(()->new CampaignManagementException(ExceptionConstants.ROLE_ACTION_NOT_FOUND));
		}
	}

    private void saveContactCampaign(CampaignEntity campaignEntity, CampaignVersionEntity campaignVersionEntity,RoleAuthorizationActionEntity authorizationActionEntity){

		if((authorizationActionEntity.getRoleAction().getName().equals(ActionTypeEnum.PUBLISH.getValue())
				&& (campaignVersionEntity.getCampaignInformation().getVersionStartDate() != null ?
				campaignVersionEntity.getCampaignInformation().getVersionStartDate().isBefore(LocalDateTime.now()) : Boolean.TRUE)) ||
				authorizationActionEntity.getRoleAction().getName().equals(ActionTypeEnum.ACTIVE_CAMPAIGN.getValue()) ||
				authorizationActionEntity.getRoleAction().getName().equals(ActionTypeEnum.CLOSE_CAMPAIGN.getValue()) ||
				authorizationActionEntity.getRoleAction().getName().equals(ActionTypeEnum.PUT_CAMPAIGN_ON_HOLD.getValue())){

			var optExEntity= customerCampaignJpaRepository.findByCampaign(campaignEntity);
			if(optExEntity.isPresent()){
				var entity=optExEntity.get();
				var exVersion=entity.getCampaignVersion();
				var oldVersionCampaignInformation= exVersion.getCampaignInformation();
				oldVersionCampaignInformation.setCampaignApprovalStatus(authorizationActionEntity.getCampaignOldApprovalStatus());
				oldVersionCampaignInformation.setCampaignStatus(authorizationActionEntity.getCampaignOldVersionStatus());
				campaignInformationRepositoryJpaAdapter.save(oldVersionCampaignInformation);
				entity.setCampaignVersion(campaignVersionEntity);
				customerCampaignJpaRepository.save(entity);
			}else{
				var newContactCampaign=new CustomerCampaignEntity();
				newContactCampaign.setCampaign(campaignEntity);
				newContactCampaign.setCampaignVersion(campaignVersionEntity);
				customerCampaignJpaRepository.save(newContactCampaign);
			}

		}



	}

}
