/* odeon_sukruo created on 18.05.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleGroup;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateCampaign;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignRuleGroupRepository;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.operator.ConjunctionOperatorEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.*;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.*;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.jpa.ContactGroupJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository.KnimeTaskTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.repository.TaskGroupJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignRuleGroupRepositoryJpaAdapter implements CampaignRuleGroupRepository {

	private final CampaignRuleGroupJpaRepository campaignRuleGroupJpaRepository;
	private final CampaignRuleRepositoryJpaAdapter campaignRuleRepositoryJpaAdapter;
	private final RuleGroupRewardRepositoryJpaAdapter ruleGroupRewardRepositoryJpaAdapter;
	private final CampaignRuleGroupOwnerProductJpaRepository ruleGroupOwnerProductJpaRepository;
	private final CampaignRuleGroupRelatedCooperationJpaRepository campaignRuleGroupRelatedCooperationJpaRepository;
	private final CampaignRuleGroupContactProductJpaRepository campaignRuleGroupContactProductJpaRepository;
	private final ContactGroupJpaRepository contactGroupJpaRepository;
	private final TaskGroupJpaRepository taskGroupJpaRepository;
	private final CampaignVersionRepositoryJpaAdapter campaignVersionRepositoryJpaAdapter;
	private final CampaignRuleGroupMatGroupJpaRepository campaignRuleGroupMatGroupJpaRepository;
	private final CampaignRuleGroupIsBankJpaRepository campaignRuleGroupIsBankJpaRepository;


	public CampaignRuleGroupEntity save(CreateCampaign createCampaign,CampaignEntity campaignEntity, CampaignVersionEntity campaignVersionEntity,
			CreateCampaign.CampaignRuleGroup campaignRuleGroup){

		var ruleGroup=new CampaignRuleGroupEntity();
		ruleGroup.setName(campaignRuleGroup.getName());
		ruleGroup.setConjunctionOperator(campaignRuleGroup.getConjunctionOperator()!=null?
				campaignRuleGroup.getConjunctionOperator(): ConjunctionOperatorEnum.AND);
		ruleGroup.setCampaign(campaignEntity);
		ruleGroup.setCampaignVersion(campaignVersionEntity);
		var savedRuleGroup=campaignRuleGroupJpaRepository.save(ruleGroup);
		savedRuleGroup.setCampaignRules(campaignRuleGroup.getAttributes().stream()
				.map(item->campaignRuleRepositoryJpaAdapter
						.createCampaignRuleAttribute(savedRuleGroup,item))
				.collect(Collectors.toSet()));
		savedRuleGroup.setReward(ruleGroupRewardRepositoryJpaAdapter
				.createRuleGroupReward(createCampaign,campaignEntity,savedRuleGroup,campaignRuleGroup));
		savedRuleGroup.setOwnerProduct(saveRuleGroupOwnerProduct(ruleGroup,campaignRuleGroup.getOwnerProduct()));
		savedRuleGroup.setRelatedCooperation(saveRuleGroupRelatedCooperation(ruleGroup,campaignRuleGroup.getRelatedCooperation()));
		savedRuleGroup.setContactProduct(saveRuleGroupContactProduct(ruleGroup,campaignRuleGroup.getContactProduct()));
		savedRuleGroup.setMatGroup(saveRuleGroupMatGroup(ruleGroup,campaignRuleGroup.getMatRuleGroup()));
		savedRuleGroup.setIsBank(saveRuleGroupIsBank(ruleGroup,campaignRuleGroup.getIsBankRuleGroup()));
        savedRuleGroup.setContactGroup(campaignRuleGroup.getContactGroupId()!=null?
				contactGroupJpaRepository
						.findById(campaignRuleGroup.getContactGroupId())
						.orElseThrow(()->new CampaignManagementException("contact.group.not.found"))
				:null);
		savedRuleGroup.setTaskGroup(campaignRuleGroup.getTaskGroupId()!=null?
				taskGroupJpaRepository.findById(campaignRuleGroup.getTaskGroupId())
						.orElseThrow(()->new CampaignManagementException("task.group.not.found"))
				:null);

		return campaignRuleGroupJpaRepository.save(savedRuleGroup);


	}

	@Override
	public List<CampaignRuleGroup> finCampaignRuleGroupsByVersionId(Long versionId){
		var campaignRuleGroupEntities=campaignRuleGroupJpaRepository.findByCampaignVersionId(versionId);
		return campaignRuleGroupEntities.stream()
				.map(CampaignRuleGroupEntity::toModel)
				.collect(Collectors.toList());
	}


	private CampaignRuleGroupOwnerProductEntity  saveRuleGroupOwnerProduct(
			CampaignRuleGroupEntity campaignRuleGroupEntity, CreateCampaign.CampaignRuleGroup campaignRuleGroup){

		if(campaignRuleGroup!=null){
			var ruleGroup=new CampaignRuleGroupOwnerProductEntity();
			ruleGroup.setRuleGroup(campaignRuleGroupEntity);
			ruleGroup.setName(campaignRuleGroup.getName());
			ruleGroup.setConjunctionOperator(campaignRuleGroup.getConjunctionOperator());
			var savedRuleGroup=ruleGroupOwnerProductJpaRepository.save(ruleGroup);
			var attributes=campaignRuleGroup.getAttributes().stream()
					.map(item->campaignRuleRepositoryJpaAdapter
							.createCampaignRuleAttribute(savedRuleGroup,item))
					.collect(Collectors.toSet());
			savedRuleGroup.setCampaignRules(attributes);
			return  savedRuleGroup;
		}
		return null;


	}



	@Override
	public List<CampaignRuleGroup> finCampaignRuleGroupsByCampaignId(Long campaignId) {
		var activeVersion=campaignVersionRepositoryJpaAdapter.findActiveVersionByCampaignId(campaignId);
		return  finCampaignRuleGroupsByVersionId(activeVersion.getId());
	}

	@Override
	public List<CampaignRuleGroup> findRuleGroupsWithAvailableCodeSetCampaignRuleGroupsByCampaignId(Long campaignId) {
		var activeVersion=campaignVersionRepositoryJpaAdapter.findActiveVersionByCampaignId(campaignId);
		var ruleGroup =   finCampaignRuleGroupsByVersionId(activeVersion.getId());

		return  ruleGroup.stream().filter(item -> discountCodeInformationExistAndNotUsed(item)
		).collect(Collectors.toList());
	}


	@Override
	public List<CampaignRuleGroup> finCampaignRuleGroupsByCampaignIdAndVersion(Long campaignId,
			Long version) {
		var v=campaignVersionRepositoryJpaAdapter.findLatestVersionRecordByCampaignIdAndVersion(campaignId,version);
		return  finCampaignRuleGroupsByVersionId(v.getId());
	}

	@Override public CampaignRuleGroup findById(Long id) {
		return campaignRuleGroupJpaRepository.findById(id)
				.orElseThrow(()->new CampaignManagementException("campaign.not.found")).toModel();
	
	}

	@Override
	public List<String> getCampaignRuleGroupNamesByCampaignId(Long campaignId) {
		var activeVersion=campaignVersionRepositoryJpaAdapter.findActiveVersionByCampaignId(campaignId);
		var campaignRuleGroupEntities=campaignRuleGroupJpaRepository.findByCampaignVersionId(activeVersion.getId());

		return campaignRuleGroupEntities.stream().map(CampaignRuleGroupEntity::getName).collect(Collectors.toList());
	}

	private CampaignRuleGroupRelatedCooperationEntity  saveRuleGroupRelatedCooperation(
			CampaignRuleGroupEntity campaignRuleGroupEntity, CreateCampaign.CampaignRuleGroup campaignRuleGroup){

		if(campaignRuleGroup!=null){
			var ruleGroup=new CampaignRuleGroupRelatedCooperationEntity();
			ruleGroup.setRuleGroup(campaignRuleGroupEntity);
			ruleGroup.setName(campaignRuleGroup.getName());
			ruleGroup.setConjunctionOperator(campaignRuleGroup.getConjunctionOperator());
			var savedRuleGroup=campaignRuleGroupRelatedCooperationJpaRepository.save(ruleGroup);
			var attributes=campaignRuleGroup.getAttributes().stream()
					.map(item->campaignRuleRepositoryJpaAdapter
							.createCampaignRuleAttribute(savedRuleGroup,item))
					.collect(Collectors.toSet());
			savedRuleGroup.setCampaignRules(attributes);
			return  savedRuleGroup;
		}
		return null;

	}

	private CampaignRuleGroupMatEntity  saveRuleGroupMatGroup(
			CampaignRuleGroupEntity campaignRuleGroupEntity, CreateCampaign.CampaignRuleGroup campaignRuleGroup){

		if(campaignRuleGroup!=null){
			var ruleGroup=new CampaignRuleGroupMatEntity();
			ruleGroup.setRuleGroup(campaignRuleGroupEntity);
			ruleGroup.setName(campaignRuleGroup.getName());
			ruleGroup.setConjunctionOperator(campaignRuleGroup.getConjunctionOperator());
			var savedRuleGroup=campaignRuleGroupMatGroupJpaRepository.save(ruleGroup);
			var attributes=campaignRuleGroup.getAttributes().stream()
					.map(item->campaignRuleRepositoryJpaAdapter
							.createCampaignRuleAttribute(savedRuleGroup,item))
					.collect(Collectors.toSet());
			savedRuleGroup.setCampaignRules(attributes);
			return  savedRuleGroup;
		}
		return null;

	}

	private CampaignRuleGroupContactProductEntity  saveRuleGroupContactProduct(
			CampaignRuleGroupEntity campaignRuleGroupEntity, CreateCampaign.CampaignRuleGroup campaignRuleGroup){

		if(campaignRuleGroup!=null){
			var ruleGroup=new CampaignRuleGroupContactProductEntity();
			ruleGroup.setRuleGroup(campaignRuleGroupEntity);
			ruleGroup.setName(campaignRuleGroup.getName());
			ruleGroup.setConjunctionOperator(campaignRuleGroup.getConjunctionOperator());
			var savedRuleGroup=campaignRuleGroupContactProductJpaRepository.save(ruleGroup);
			var attributes=campaignRuleGroup.getAttributes().stream()
					.map(item->campaignRuleRepositoryJpaAdapter
							.createCampaignRuleAttribute(savedRuleGroup,item))
					.collect(Collectors.toSet());
			savedRuleGroup.setCampaignRules(attributes);
			return  savedRuleGroup;
		}
		return null;


	}

	private CampaignRuleGroupIsBankEntity  saveRuleGroupIsBank(CampaignRuleGroupEntity campaignRuleGroupEntity, CreateCampaign.CampaignRuleGroup campaignRuleGroup){

		if(campaignRuleGroup!=null){
			var ruleGroup=new CampaignRuleGroupIsBankEntity();
			ruleGroup.setRuleGroup(campaignRuleGroupEntity);
			ruleGroup.setName(campaignRuleGroup.getName());
			ruleGroup.setConjunctionOperator(campaignRuleGroup.getConjunctionOperator());
			var savedRuleGroup=campaignRuleGroupIsBankJpaRepository.save(ruleGroup);
			var attributes= campaignRuleGroup.getAttributes().stream()
					.map(item->campaignRuleRepositoryJpaAdapter
							.createCampaignRuleAttribute(savedRuleGroup,item))
					.collect(Collectors.toSet());
			savedRuleGroup.setCampaignRules(attributes);
			return  savedRuleGroup;
		}
		return null;

	}

	private Boolean discountCodeInformationExistAndNotUsed(CampaignRuleGroup campaignRuleGroup){
		return campaignRuleGroup.getCampaignReward() != null
				&& campaignRuleGroup.getCampaignReward().getCampaignRewardDiscount() != null
				&& campaignRuleGroup.getCampaignReward().getCampaignRewardDiscount().getDiscountCodeInformation() != null
				&& !campaignRuleGroup.getCampaignReward().getCampaignRewardDiscount().getDiscountCodeInformation().getIsUsed();
	}
}
