package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.UsedCodeGroupInformation;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.*;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.DiscountCodeInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.RuleGroupRewardEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.RuleGroupRewardJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.Specification;
import com.anadolusigorta.campaignmanagement.infrastructure.common.enums.UploadFileEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateGiftCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.GiftCodeInformationRepository;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.GiftCodeInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.GiftCodeInformationJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.GiftCodeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository.GiftCodeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository.RewardGiftTicketTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.RewardCompanyInformationJpaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiftCodeInformationRepositoryJpaAdapter implements GiftCodeInformationRepository {

	private final GiftCodeInformationJpaRepository giftCodeInformationJpaRepository;
	private final GiftCodeJpaRepository giftCodeJpaRepository;
	private final RewardGiftTicketTypeJpaRepository giftCodeRewardTypeJpaRepository;
	private final RewardCompanyInformationJpaRepository rewardCompanyInformationJpaRepository;
	private final RuleGroupRewardJpaRepository ruleGroupRewardJpaRepository;

	@Override
	public List<GiftCodeInformation> getAllGiftCodeInformation() {
		return giftCodeInformationJpaRepository.findAll().stream()
				.sorted(Comparator.comparing(GiftCodeInformationEntity::getCreatedAt).reversed())
				.map(GiftCodeInformationEntity::toModel).peek(item -> {
					item.setTotalCodeCount(giftCodeJpaRepository.countByGiftCodeInformationId(item.getId()));
					item.setUsedCodeCount(
							giftCodeJpaRepository.countByGiftCodeInformationIdAndCodeStatusEnum(item.getId(), CodeStatusEnum.USED) +
							giftCodeJpaRepository.countByGiftCodeInformationIdAndCodeStatusEnum(item.getId(), CodeStatusEnum.PROPOSED));
				}).collect(Collectors.toList());
	}

	@Override
	public PageContent<GiftCodeInformation> getPageableGiftCodeInformationList(Pageable pageable) {
		var giftCodeInformations =  giftCodeInformationJpaRepository
				.findAll(matchedCriteria(pageable),PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize()));
		return PageContent.<GiftCodeInformation>builder()
				.content(giftCodeInformations.getContent().stream()
						.map(GiftCodeInformationEntity::toModel).peek(item -> {
							item.setTotalCodeCount(giftCodeJpaRepository.countByGiftCodeInformationId(item.getId()));
							item.setUsedCodeCount(
									giftCodeJpaRepository.countByGiftCodeInformationIdAndCodeStatusEnum(item.getId(), CodeStatusEnum.USED) +
											giftCodeJpaRepository.countByGiftCodeInformationIdAndCodeStatusEnum(item.getId(), CodeStatusEnum.PROPOSED));
						})
						.collect(Collectors.toList()))
				.page(pageable.getPageNumber())
				.size(pageable.getPageSize())
				.totalItems(giftCodeInformations.getTotalElements())
				.totalPages(giftCodeInformations.getTotalPages())
				.sort(pageable.getSort())
				.build();
	}

	private Specification<GiftCodeInformationEntity> matchedCriteria(Pageable pageable) {

		return (root, query, builder) -> {

			List<Predicate> predicates = new ArrayList<>();

			sorting(pageable, root, query, builder);

			return builder.and(predicates.toArray(new Predicate[0]));

		};
	}

	private static void sorting(Pageable pageable, Root<GiftCodeInformationEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		var sort= pageable.getSort().get().findFirst();
		if(sort.isPresent()){
			var sortName=sort.get().getProperty();
			var direction=sort.get().getDirection();

			var path = switch (sortName) {
				case "id" -> "id";
				case "companyInformationName" -> "rewardCompanyInformation.name";
				case "rewardGiftTicketTypeName" -> "giftCodeRewardType.name";
				default -> "id";
			};

			final Path<String> name=getPath(String.class, root, path);
			if(direction.equals(Sort.Direction.ASC)){
				query.orderBy(builder.asc(name));

			}else {
				query.orderBy(builder.desc(name));

			}

		}
	}

	private static <T, R> Path<R> getPath(Class<R> resultType, Path<T> root, String path) {
		String[] pathElements = path.split("\\.");
		Path<?> retVal = root;
		for (String pathEl : pathElements) {
			retVal = (Path<R>) retVal.get(pathEl);
		}
		return (Path<R>) retVal;
	}

	@Override
	public List<UsedCodeGroupInformation> getCampaignListByUsedGiftCodeInformationId(Long giftCodeInformationId) {
		var usedCampaigns = ruleGroupRewardJpaRepository.findByGift_GiftCodeInformation_Id(giftCodeInformationId);
		var filteredUsedCampaigns = usedCampaigns.stream().filter(item -> item.getRuleGroup().getCampaignVersion().getIsActiveVersion()).collect(Collectors.toList());
		return filteredUsedCampaigns.stream().map(RuleGroupRewardEntity::toDiscountInformationModel).collect(Collectors.toList());
	}

	@Override
	public List<GiftCode> getAllGiftCodeByGiftCodeInformationId(Long giftCodeInformationId) {
		return giftCodeJpaRepository.findAllByGiftCodeInformationId(giftCodeInformationId).stream()
				.map(GiftCodeEntity::toModel).collect(Collectors.toList());
	}

	@Override
	public List<GiftCode> getAllGiftCodes() {
		return giftCodeJpaRepository.findAll()
				.stream()
				.map(GiftCodeEntity::toModel)
				.collect(Collectors.toList());
	}

	@Override
	public List<GiftCode> findRewardGiftCodes(Long rewardGiftId) {
		return giftCodeJpaRepository.findAllBySaleRewardGiftId(rewardGiftId)
				.stream()
				.map(GiftCodeEntity::toModel)
				.collect(Collectors.toList());
	}

	@Override
	public PageContent<GiftCode> getAllGiftCodes(String codeGroupName, String contactNumber, String code, String codeStatus, Pageable pageable) {

		Page<GiftCodeEntity> pageEntity;

		if(ObjectUtils.isEmpty(code) && ObjectUtils.isEmpty(contactNumber)){
			pageEntity = giftCodeJpaRepository.findAll(pageable);
		}
		else if(ObjectUtils.isEmpty(code)){
			pageEntity = giftCodeJpaRepository.findAllByContactNumberContains(contactNumber,pageable);
		}
		else if(ObjectUtils.isEmpty(contactNumber)){
			pageEntity = giftCodeJpaRepository.findAllByCodeContains(code,pageable);
		}
		else{
			pageEntity = giftCodeJpaRepository.findAllByCodeContainsAndContactNumberContains(code,contactNumber,pageable);
		}

		return PageContent.<GiftCode>builder()
				.content(pageEntity.getContent().stream()
						.map(GiftCodeEntity::toModel)
						.collect(Collectors.toList()))
				.totalPages(pageEntity.getTotalPages())
				.totalItems(pageEntity.getTotalElements())
				.size(pageEntity.getSize())
				.page(pageEntity.getNumber())
				.build();
	}

	@Override
	public GiftCodeInformation saveGiftCodeInformation(CreateGiftCodeInformation createGiftCodeInformation) {
		GiftCodeInformationEntity giftCodeInformationEntity;
		if (createGiftCodeInformation.getId() == null) {
			giftCodeInformationEntity = new GiftCodeInformationEntity();
			var rewardCompanyInformation = rewardCompanyInformationJpaRepository
					.findById(createGiftCodeInformation.getRewardCompanyInformationId()).orElseThrow();
			var giftCodeRewardType = giftCodeRewardTypeJpaRepository
					.findById(createGiftCodeInformation.getRewardGiftTicketTypeId()).orElseThrow();
			giftCodeInformationEntity.setRewardCompanyInformation(rewardCompanyInformation);
			giftCodeInformationEntity.setGiftCodeRewardType(giftCodeRewardType);
			giftCodeInformationJpaRepository.save(giftCodeInformationEntity);
		} else {
			giftCodeInformationEntity = giftCodeInformationJpaRepository.findById(createGiftCodeInformation.getId())
					.orElseThrow();
		}
		CompletableFuture.supplyAsync(() -> {
			saveGiftCode(createGiftCodeInformation, giftCodeInformationEntity);
			return null;
		});
		giftCodeInformationEntity.setUploadFileEnum(UploadFileEnum.UPLOADING);
		giftCodeInformationJpaRepository.save(giftCodeInformationEntity);
		return giftCodeInformationEntity.toModel();
	}

	@Async
	public void saveGiftCode(CreateGiftCodeInformation createGiftCodeInformation, GiftCodeInformationEntity giftCodeInformationEntity) {
		try {
			var codes=createGiftCodeInformation.getGiftCodes();
			var codesEntity=codes.stream()
							.map(item->GiftCodeEntity.builder()
									.giftCodeInformation(giftCodeInformationEntity)
									.codeStatusEnum(CodeStatusEnum.UNUSED).code(item.getCode()).qrCodeUrl(item.getLink())
									.giftCodeInformation(giftCodeInformationEntity)
									.build())
									.toList();
			giftCodeJpaRepository.saveAll(codesEntity);
		} catch (Exception e) {
			giftCodeInformationEntity.setUploadFileEnum(UploadFileEnum.NOT_UPLOADED);
			giftCodeInformationJpaRepository.save(giftCodeInformationEntity);
			return;
		}
		giftCodeInformationEntity.setUploadFileEnum(UploadFileEnum.UPLOADED);
		giftCodeInformationJpaRepository.save(giftCodeInformationEntity);
	}

	@Override
	public GiftCode getAvailableGiftCode(GiftCodeInformation giftCodeInformation) {
		var giftCode = giftCodeJpaRepository
				.findTopByGiftCodeInformationIdAndContactNumberIsNull(giftCodeInformation.getId())
				.orElse(null);
		return giftCode != null ? giftCode.toModel() : null;
	}

	@Override
	public List<GiftCode> getAvailableGiftCodeByLimit(GiftCodeInformation giftCodeInformation, Long count) {

		var page = giftCodeJpaRepository
				.findByGiftCodeInformationIdAndContactNumberIsNull(giftCodeInformation.getId(), PageRequest.of(0,count.intValue()));
		var content= page.getContent();
		return content.stream()
						.map(GiftCodeEntity::toModel)
						.toList();
	}

	@Override
	public void setCustomerToGiftCode(GiftCode giftCode, String contactNumber) {
		var giftCodeEntity = giftCodeJpaRepository.findById(giftCode.getId()).orElse(null);
		if (giftCodeEntity != null) {
			giftCodeEntity.setContactNumber(contactNumber);
			giftCodeJpaRepository.save(giftCodeEntity);
		} else {
			log.info("Couldn't set customer '" + contactNumber + "' to giftCodeEntity with id '" + giftCode.getId()
					+ "'");
		}
	}

	@Override
	public void assignCodesToContact(String contactNumber, Long codeInformationId, List<String> giftCodes) {
		giftCodes.forEach(item->{
			var codeEntityOptional= giftCodeJpaRepository
					.findByCodeAndGiftCodeInformationIdAndContactNumberIsNull(item,codeInformationId);
			if(codeEntityOptional.isPresent()){
				var giftCodeEntity=codeEntityOptional.get();
				giftCodeEntity.setContactNumber(contactNumber);
				giftCodeJpaRepository.save(giftCodeEntity);
			} else {
				log.info("Couldn't set customer '" + contactNumber + "' to giftCodeEntity with code '" + item
						+ "'");
			}
		});
	}

	@Async
	public void parseAndSaveGiftCodesFromExcel(String data, GiftCodeInformationEntity giftCodeInformationEntity) {
		byte[] decodedBytes = Base64.getDecoder().decode(data);
		InputStream inputStream = new ByteArrayInputStream(decodedBytes);
		Workbook workbook = null;
		int rowIndex = 0;

		try {
			workbook = new XSSFWorkbook(inputStream);
		} catch (Exception e) {
			throw new CampaignManagementException(ExceptionConstants.CAMPAIGN_CODE_EXCEL_ERROR);
		}

		if (workbook != null) {
			DataFormatter dataFormatter = new DataFormatter();
			Sheet sheet = workbook.getSheetAt(0);
			for (Row currentRow : sheet) {
				if (rowIndex == 0) {
					rowIndex += 1;
					continue;
				}
				if (currentRow.getCell(0) != null) {
					currentRow.getCell(0).setCellType(CellType.STRING);
					String code = dataFormatter.formatCellValue(currentRow.getCell(0));
					String qrCodeUrl = dataFormatter
							.formatCellValue(currentRow.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));

						giftCodeJpaRepository.save(GiftCodeEntity.builder()
								.giftCodeInformation(giftCodeInformationEntity)
								.codeStatusEnum(CodeStatusEnum.UNUSED).code(code).qrCodeUrl(qrCodeUrl).build());

				}
			}
		}
	}
}
