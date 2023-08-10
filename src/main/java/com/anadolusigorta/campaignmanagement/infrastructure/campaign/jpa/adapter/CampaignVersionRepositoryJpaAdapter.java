/* odeon_sukruo created on 11.05.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignCreateModeType;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignVersion;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateCampaign;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignRuleGroupEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignVersionEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignVersionJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class CampaignVersionRepositoryJpaAdapter {

	private final CampaignVersionJpaRepository campaignVersionJpaRepository;

	public CampaignVersionEntity save(CreateCampaign createCampaign,CampaignEntity campaignEntity){
		CampaignVersionEntity  version=null;
		version=saveCampaignVersionEntity(createCampaign,campaignEntity);
		return version;
	}



	public CampaignVersionEntity saveCampaignVersionEntity(CreateCampaign createCampaign
			,CampaignEntity campaignEntity) {
		var v=0L;
		if(createCampaign.getId()!=null){
            var currentVersionOpt=campaignVersionJpaRepository
					.findByCampaignIdAndIsActiveVersionTrue(createCampaign.getId());
			if(currentVersionOpt.isPresent()){
				var currentVersion=currentVersionOpt.get();
				currentVersion.setIsActiveVersion(Boolean.FALSE);
				v=currentVersion.getVersion();
				campaignVersionJpaRepository.save(currentVersion);
				if(createCampaign.getCampaignInformation().getCreateMode().equals(CampaignCreateModeType.NEW_VERSION)){
					v=currentVersion.getVersion()+1;
				}
			}

		}
		var versionEntity = new CampaignVersionEntity();
		versionEntity.setCampaign(campaignEntity);
		versionEntity.setVersion(v);
		versionEntity.setIsActiveVersion(Boolean.TRUE);
		return campaignVersionJpaRepository.save(versionEntity);
	}

	public CampaignVersionEntity updateCampaignVersion(CampaignVersionEntity campaignVersionEntity,
			CampaignEntity campaignEntity, CampaignInformationEntity campaignInformationEntity,
			Set<CampaignRuleGroupEntity> campaignRuleGroupEntitySet){
		campaignVersionEntity.setCampaign(campaignEntity);
		campaignVersionEntity.setCampaignInformation(campaignInformationEntity);
		campaignVersionEntity.setCampaignRuleGroups(campaignRuleGroupEntitySet);
		return campaignVersionJpaRepository.save(campaignVersionEntity);
	}

	public CampaignVersionEntity findById(Long id){
		return campaignVersionJpaRepository.findById(id)
				.orElseThrow(()->new CampaignManagementException(ExceptionConstants.CAMPAIIGN_VERSION_NOT_FOUND));


	}

	public CampaignVersionEntity findActiveVersionByCampaignId(Long campaignId){
		return campaignVersionJpaRepository.findByCampaignIdAndIsActiveVersionTrue(campaignId)
				.orElseThrow(()->new CampaignManagementException(ExceptionConstants.CAMPAIIGN_VERSION_NOT_FOUND));


	}



	public CampaignVersionEntity findVersionByCampaignIdAndVersionId(Long campaignId,Long versionId){
		return campaignVersionJpaRepository.findByIdAndCampaignId(versionId,campaignId)
				.orElseThrow(()->new CampaignManagementException(ExceptionConstants.CAMPAIIGN_VERSION_NOT_FOUND));


	}

	public CampaignVersion findLatestVersionRecordByCampaignIdAndVersion(Long campaignId,Long version){
		return findCampaignVersions(campaignId).stream()
				.filter(item->item.getVersion().equals(version))
				.findFirst()
				.orElseThrow(()->new CampaignManagementException(ExceptionConstants.CAMPAIIGN_VERSION_NOT_FOUND));

	}

	public List<CampaignVersion>  findCampaignVersions(Long campaignId){

		var versionEntity= campaignVersionJpaRepository.findVersionByCampaignId(campaignId);
		var versionGroup= new AtomicReference<>(
				versionEntity.stream().collect(groupingBy(CampaignVersionEntity::getVersion,
						maxBy(comparingLong(CampaignVersionEntity::getId)))));

		return versionGroup.get().keySet().stream().map(item->{
			var v= versionGroup.get().get(item).orElseThrow(()->new CampaignManagementException("campaign.not.found"));
			return CampaignVersion.builder()
					.version(item)
					.id(v.getId())
					.isActiveVersion(v.getIsActiveVersion())
					.build();
		}).collect(Collectors.toList());


	}

	private void saveOldVersionStatuses(CreateCampaign createCampaign, CampaignEntity campaignEntity) {
		if(createCampaign.getId()!=null){
			var exVersion= findActiveVersionByCampaignId(campaignEntity.getId());
			exVersion.setIsActiveVersion(false);
			campaignVersionJpaRepository.save(exVersion);

		}
	}
}
