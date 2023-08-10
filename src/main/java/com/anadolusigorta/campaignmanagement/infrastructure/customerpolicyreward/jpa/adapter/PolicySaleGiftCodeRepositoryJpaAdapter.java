package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.UsedCodeGroupInformation;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.CreatePolicySaleGiftCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCode;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCodeInformationDetail;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.port.PolicySaleGiftCodeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.common.enums.UploadFileEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity.PolicySaleGiftCodeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity.PolicySaleGiftCodeInformationDetailEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity.PolicySaleGiftCodeInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PolicySaleGiftCodeRepositoryJpaAdapter implements PolicySaleGiftCodeRepository {

	private final PolicySaleGiftCodeInformationJpaRepository policySaleGiftCodeInformationJpaRepository;
	private final PolicySaleGiftCodeJpaRepository policySaleGiftCodeJpaRepository;
	private final PolicySaleRewardGiftTicketTypeJpaRepository policySaleRewardGiftTicketTypeJpaRepository;
	private final PolicySaleRewardCompanyInformationJpaRepository policySaleRewardCompanyInformationJpaRepository;

	private final PolicySaleGiftCodeInformationDetailJpaRepository policySaleGiftCodeInformationDetailJpaRepository;

	public List<PolicySaleGiftCodeInformation> getAllPolicySaleGiftCodeInformation() {
		return policySaleGiftCodeInformationJpaRepository.findAll().stream()
				.sorted(Comparator.comparing(PolicySaleGiftCodeInformationEntity::getCreatedAt).reversed())
				.map(PolicySaleGiftCodeInformationEntity::toModel).map(item -> {
					item.setTotalCodeCount(
							policySaleGiftCodeJpaRepository.countByPolicySaleGiftCodeInformationId(item.getId()));
					item.setUsedCodeCount(policySaleGiftCodeJpaRepository
							.countByPolicySaleGiftCodeInformationIdAndCodeStatus(item.getId(), CodeStatusEnum.USED)
							+ policySaleGiftCodeJpaRepository.countByPolicySaleGiftCodeInformationIdAndCodeStatus(
									item.getId(), CodeStatusEnum.PROPOSED));
					return item;
				}).collect(Collectors.toList());
	}

	@Override
	public List<UsedCodeGroupInformation> getCampaignListByUsedPolicySaleGiftCodeInformationId(
			Long policySaleGiftCodeInformationId) {
		return new ArrayList<>();
	}

	@Override
	public PolicySaleGiftCodeInformation savePolicySaleGiftCodeInformation(
			CreatePolicySaleGiftCodeInformation createPolicySaleGiftCodeInformation,
			String activeUser) {
		PolicySaleGiftCodeInformationEntity policySaleGiftCodeInformationEntity;
		if (createPolicySaleGiftCodeInformation.getId() == null) {
			policySaleGiftCodeInformationEntity = new PolicySaleGiftCodeInformationEntity();
			var rewardCompanyInformation = policySaleRewardCompanyInformationJpaRepository
					.findById(createPolicySaleGiftCodeInformation.getRewardCompanyInformationId()).orElseThrow();
			var giftCodeRewardType = policySaleRewardGiftTicketTypeJpaRepository
					.findById(createPolicySaleGiftCodeInformation.getRewardGiftTicketTypeId()).orElseThrow();
			policySaleGiftCodeInformationEntity.setPolicySaleRewardCompanyInformationEntity(rewardCompanyInformation);
			policySaleGiftCodeInformationEntity.setPolicySaleGiftCodeRewardType(giftCodeRewardType);
			policySaleGiftCodeInformationJpaRepository.save(policySaleGiftCodeInformationEntity);
		} else {
			policySaleGiftCodeInformationEntity = policySaleGiftCodeInformationJpaRepository
					.findById(createPolicySaleGiftCodeInformation.getId()).orElseThrow();
		}
		CompletableFuture.supplyAsync(() -> {
			saveGiftCodeExcel(createPolicySaleGiftCodeInformation.getGiftCodes(),
					policySaleGiftCodeInformationEntity);
			return null;
		});
		policySaleGiftCodeInformationEntity.setUploadFileEnum(UploadFileEnum.UPLOADING);
		policySaleGiftCodeInformationJpaRepository.save(policySaleGiftCodeInformationEntity);

		savePolicySaleGiftCodeDetail(createPolicySaleGiftCodeInformation, activeUser, policySaleGiftCodeInformationEntity);

		return policySaleGiftCodeInformationEntity.toModel();
	}

	private void savePolicySaleGiftCodeDetail(CreatePolicySaleGiftCodeInformation createPolicySaleGiftCodeInformation, String activeUser, PolicySaleGiftCodeInformationEntity policySaleGiftCodeInformationEntity) {
		PolicySaleGiftCodeInformationDetailEntity policySaleGiftCodeInformationDetail = new PolicySaleGiftCodeInformationDetailEntity();
		policySaleGiftCodeInformationDetail.setUpdater(activeUser);
		policySaleGiftCodeInformationDetail.setCodeCount(createPolicySaleGiftCodeInformation.getGiftCodes() != null ? (long) createPolicySaleGiftCodeInformation.getGiftCodes().size() : 0L);
		policySaleGiftCodeInformationDetail.setDescription("Oluşturulan kod sayısı: " + policySaleGiftCodeInformationDetail.getCodeCount());
		policySaleGiftCodeInformationDetail.setPolicySaleGiftCodeInformation(policySaleGiftCodeInformationEntity);
		policySaleGiftCodeInformationDetailJpaRepository.save(policySaleGiftCodeInformationDetail);
	}

	@Async
	public void saveGiftCodeExcel(List<CreatePolicySaleGiftCodeInformation.GiftCode> codes,
			PolicySaleGiftCodeInformationEntity policySaleGiftCodeInformationEntity) {
		try {
			saveGiftCodesFromCsv(codes, policySaleGiftCodeInformationEntity);
		} catch (Exception e) {
			policySaleGiftCodeInformationEntity.setUploadFileEnum(UploadFileEnum.NOT_UPLOADED);
			policySaleGiftCodeInformationJpaRepository.save(policySaleGiftCodeInformationEntity);
			return;
		}
		policySaleGiftCodeInformationEntity.setUploadFileEnum(UploadFileEnum.UPLOADED);
		policySaleGiftCodeInformationJpaRepository.save(policySaleGiftCodeInformationEntity);
	}

	@Async
	public void saveGiftCodesFromCsv(List<CreatePolicySaleGiftCodeInformation.GiftCode> codes,
			PolicySaleGiftCodeInformationEntity policySaleGiftCodeInformationEntity) {
		/*byte[] decodedBytes = Base64.getDecoder().decode(data);
		InputStream inputStream = new ByteArrayInputStream(decodedBytes);
		Workbook workbook = null;
		int rowIndex = 0;
		DataFormatter dataFormatter = new DataFormatter();

		try {
			workbook = new XSSFWorkbook(inputStream);
		} catch (Exception e) {
			throw new CampaignManagementException(ExceptionConstants.CAMPAIGN_CODE_EXCEL_ERROR);
		}

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
				var existingCode = policySaleGiftCodeJpaRepository
						.findTopByPolicySaleGiftCodeInformationIdAndCodeAndContactNumberIsNullAndIsActiveTrue(
								policySaleGiftCodeInformationEntity.getId(), code)
						.orElse(null);
				if (existingCode == null) {
					policySaleGiftCodeJpaRepository.save(PolicySaleGiftCodeEntity.builder()
							.policySaleGiftCodeInformation(policySaleGiftCodeInformationEntity).isActive(Boolean.TRUE)
							.codeStatus(CodeStatusEnum.UNUSED).code(code).qrCodeUrl(qrCodeUrl).build());
				}
			}
		}*/
		var giftCodes = codes.stream().map(item ->
				PolicySaleGiftCodeEntity.builder()
						.policySaleGiftCodeInformation(policySaleGiftCodeInformationEntity).isActive(Boolean.TRUE)
						.codeStatus(CodeStatusEnum.UNUSED).code(item.getCode()).qrCodeUrl(item.getLink())
						.build()).collect(Collectors.toList());

		policySaleGiftCodeJpaRepository.saveAll(giftCodes);
	}

	@Override
	public List<PolicySaleGiftCode> getAllGiftCodeByGiftCodeInformationId(Long giftCodeInformationId) {
		return policySaleGiftCodeJpaRepository.findAllByPolicySaleGiftCodeInformationId(giftCodeInformationId).stream()
				.map(PolicySaleGiftCodeEntity::toModel).collect(Collectors.toList());
	}
}
