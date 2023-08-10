package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateDiscountCodeInformation;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.UsedCodeGroupInformation;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.DiscountCodeInformationRepository;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.*;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.*;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.*;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.codegenerator.CodeGenerator;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.adapter.DiscountCodeRepositoryJpaAdapter;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.DiscountCodeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository.CodeKindJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository.CodeTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository.DiscountCodeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.common.enums.UploadFileEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.DiscountCode;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DiscountCodeInformationRepositoryJpaAdapter implements DiscountCodeInformationRepository {

	private final DiscountCodeInformationJpaRepository discountCodeInformationJpaRepository;
	private final CodeTypeJpaRepository codeTypeJpaRepository;
	private final DiscountCodeRepositoryJpaAdapter discountCodeRepositoryJpaAdapter;
	private final DiscountCodeJpaRepository discountCodeJpaRepository;
	private final CodeKindJpaRepository codeKindJpaRepository;
	private final CampaignRuleGroupJpaRepository campaignRuleGroupJpaRepository;
	private final RuleGroupRewardJpaRepository ruleGroupRewardJpaRepository;
	private final DiscountCodeInformationDetailJpaRepository discountCodeInformationDetailJpaRepository;

	private final CodeGenerator codeGenerator;

	@Override
	public Optional<com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCode> findDiscountCodeByDiscountCodeInformation(Long discountInformationId, String code) {
		return discountCodeJpaRepository
				.findByDiscountCodeInformationIdAndCode(discountInformationId, code)
				.map(DiscountCodeEntity::toModel);
	}

	@Override
	public List<com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCode> getAllDiscountCodeByDiscountCodeInformation(Long discountInformationId) {
		return discountCodeJpaRepository.findByDiscountCodeInformationId(discountInformationId).stream()
				.map(DiscountCodeEntity::toModel)
				.collect(Collectors.toList());
	}

	@Override
	public List<com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCode> getAllDiscountCodes() {
		return discountCodeJpaRepository.findAll().stream().map(DiscountCodeEntity::toModel)
				.collect(Collectors.toList());
	}

	@Override
	public PageContent<com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCode> getAllDiscountCodes(String codeGroupName, String contactNumber, String code, String codeStatus, Pageable pageable) {

		Page<DiscountCodeEntity> pageEntity;

		if(ObjectUtils.isEmpty(code) && ObjectUtils.isEmpty(contactNumber)){
			 pageEntity = discountCodeJpaRepository.findAll(pageable);
		}
		else if(ObjectUtils.isEmpty(code)){
			pageEntity = discountCodeJpaRepository.findAllByContactNumberContains(contactNumber,pageable);
		}
		else if(ObjectUtils.isEmpty(contactNumber)){
			pageEntity = discountCodeJpaRepository.findAllByCodeContains(code,pageable);
		}
		else{
			pageEntity = discountCodeJpaRepository.findAllByCodeContainsAndContactNumberContains(code,contactNumber,pageable);
		}
		
		return PageContent.<com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCode>builder()
				.content(pageEntity.getContent().stream()
						.map(DiscountCodeEntity::toModel)
						.collect(Collectors.toList()))
				.totalPages(pageEntity.getTotalPages())
				.totalItems(pageEntity.getTotalElements())
				.size(pageEntity.getSize())
				.page(pageEntity.getNumber())
				.build();
	}

	@Override
	public PageContent<DiscountCodeInformation> getDiscountCodeInformationList(DiscountCodeInformationCriteria discountCodeInformationCriteria, Pageable pageable) {
		var discountCodeInformations =  discountCodeInformationJpaRepository
				.findAll(matchedCriteria(discountCodeInformationCriteria,pageable),PageRequest.of(pageable.getPageNumber()-1
						, pageable.getPageSize()
						, pageable.getSort()));
		return PageContent.<DiscountCodeInformation>builder()
				.content(collect(discountCodeInformations))
				.page(pageable.getPageNumber())
				.sort(pageable.getSort())
				.size(pageable.getPageSize())
				.totalItems(discountCodeInformations.getTotalElements())
				.totalPages(discountCodeInformations.getTotalPages())
				.build();
	}

	private List<DiscountCodeInformation> collect(Page<DiscountCodeInformationEntity> discountCodeInformations) {
		return discountCodeInformations.getContent().stream()
				.map(DiscountCodeInformationEntity::toModel)
				.collect(Collectors.toList());
	}

	private Specification<DiscountCodeInformationEntity> matchedCriteria(DiscountCodeInformationCriteria discountCodeInformationCriteria, Pageable pageable) {

		return (root, query, builder) -> {

			List<Predicate> predicates = predicating(discountCodeInformationCriteria, root, builder);

			sorting(pageable, root, query, builder);

			return builder.and(predicates.toArray(new Predicate[0]));

		};
	}

	private static List<Predicate> predicating(DiscountCodeInformationCriteria discountCodeInformationCriteria, Root<DiscountCodeInformationEntity> root, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<>();

		if(discountCodeInformationCriteria.getId()!=null){
			final Path<Long> id=getPath(Long.class, root, "id");
			predicates.add(builder.equal(id, discountCodeInformationCriteria.getId()));
		}

		if(discountCodeInformationCriteria.getCodeGroupName()!=null && !discountCodeInformationCriteria.getCodeGroupName().isEmpty()){
			final Path<String> name=getPath(String.class, root, "codeGroupName");
			predicates.add(builder.like(builder.lower(name),("%"+ discountCodeInformationCriteria.getCodeGroupName()+"%").toLowerCase()));

		}

		return predicates;
	}

	private static void sorting(Pageable pageable, Root<DiscountCodeInformationEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		var sort= pageable.getSort().get().findFirst();
		if(sort.isPresent()){
			var sortName=sort.get().getProperty();
			var direction=sort.get().getDirection();

			if(sortName.equals("codeTypeName")){
				sortName = "codeType";
			}

			final Path<String> name=getPath(String.class, root, sortName);
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

	private Long getSum(DiscountCodeInformation discountCodeInformation) {
		return (long) discountCodeJpaRepository
				.findByDiscountCodeInformationId(discountCodeInformation.getId())
				.size();
	}

	@Override
	public PageContent<DiscountCodeInformation> getDiscountCodeInformationList(String codeGroupName,String contactNumber,Pageable pageable) {

		var codeInformationList=discountCodeInformationJpaRepository.findAllByCodeGroupNameContains(codeGroupName,pageable);

		return PageContent.<DiscountCodeInformation>builder()
				.content(codeInformationList.getContent().stream()
						.map(DiscountCodeInformationEntity::toModel)
						.collect(Collectors.toList()))
				.page(codeInformationList.getNumber())
				.size(codeInformationList.getSize())
				.totalItems(codeInformationList.getTotalElements())
				.totalPages(codeInformationList.getTotalPages())
				.sort(pageable.getSort())
				.build();
	}

	@Override
	public List<UsedCodeGroupInformation> getCampaignListByUsedDiscountCodeInformationId(
			Long discountCodeInformationId) {
		var usedCampaigns = ruleGroupRewardJpaRepository
				.findByDiscount_DiscountCodeInformation_Id(discountCodeInformationId);
		var filteredUsedCampaigns = usedCampaigns.stream()
				.filter(item -> item.getRuleGroup().getCampaignVersion().getIsActiveVersion())
				.toList();
		return filteredUsedCampaigns.stream().map(RuleGroupRewardEntity::toDiscountInformationModel)
				.collect(Collectors.toList());
	}

	@Override
	public List<DiscountCodeInformation> getAllActiveDiscountCodeInformation() {
		return discountCodeInformationJpaRepository.findAllByCodeExpirationDateIsAfter(LocalDateTime.now()).stream()
				.filter(item -> item.getUploadFileStatus() == null || item.getUploadFileStatus() == UploadFileEnum.UPLOADED)
				.sorted(Comparator.comparing(DiscountCodeInformationEntity::getCreatedAt).reversed())
				.map(DiscountCodeInformationEntity::toModel).collect(Collectors.toList());
	}

	@Override
	public DiscountCodeInformation saveDiscountCodeInformation(
			CreateDiscountCodeInformation createDiscountCodeInformation) {

		DiscountCodeInformationEntity discountCodeInformationEntity=
				createDiscountCodeInformation.getId()!=null?discountCodeInformationJpaRepository
				.findById(createDiscountCodeInformation.getId())
						.orElseThrow(()->new CampaignManagementException(ExceptionConstants.DISCOUNT_CODE_INFORMATION_NOT_FOUND))
						:new DiscountCodeInformationEntity();

		discountCodeInformationEntity.setUploadStartTime(LocalDateTime.now());
		discountCodeInformationEntity.setCodeGroupName(createDiscountCodeInformation.getCodeGroupName());
			var codeType = codeTypeJpaRepository.findById(createDiscountCodeInformation.getCodeTypeId())
					.orElseThrow(()->new CampaignManagementException(ExceptionConstants.DISCOUNT_CODE_INFORMATION_NOT_FOUND));
			discountCodeInformationEntity.setCodeType(codeType);

			var codeKind = codeKindJpaRepository.findByName("CODE_CREATED_BY_CAMPAIGN").orElseThrow();
			discountCodeInformationEntity.setCodeKind(codeKind);
			discountCodeInformationEntity.setCodeExpirationDate(createDiscountCodeInformation.getCodeExpirationDate());
			discountCodeInformationEntity.setIsPairedWithCustomers(createDiscountCodeInformation.isPairedWithCustomers());
		   discountCodeInformationEntity.setCodeCharacterLength(createDiscountCodeInformation.getCodeCharacterLength());
		   discountCodeInformationEntity.setUploadFileStatus(UploadFileEnum.UPLOADING);

		discountCodeInformationEntity=discountCodeInformationJpaRepository.save(discountCodeInformationEntity);
		return discountCodeInformationEntity.toModel();
	}

	@Async
	public void createDiscountCodeInformationDetail(CreateDiscountCodeInformation createDiscountCodeInformation,
			DiscountCodeInformation discountCodeInformation, String userFullName) {
		var discountCodeInformationEntity = discountCodeInformationJpaRepository
				.findById(discountCodeInformation.getId())
				.orElseThrow(()->new CampaignManagementException(ExceptionConstants.DISCOUNT_CODE_INFORMATION_NOT_FOUND));

		var discountCodeInformationDetailEntity = new DiscountCodeInformationDetailEntity();

		discountCodeInformationDetailEntity.setUpdater(userFullName);
		discountCodeInformationDetailEntity.setDiscountCodeInformation(discountCodeInformationEntity);

		var desription="";
		var totalCodeCount=0L;
		switch (CodeTypeEnum.of(discountCodeInformationEntity.getCodeType().getName())){
			case THIRD_PARTY_CODE ->{
				desription=String.format("Yüklenen kod sayısı:%s",createDiscountCodeInformation.getDiscountCodes().size());
				totalCodeCount=createDiscountCodeInformation.getDiscountCodes().size();
			}
			case UNLIMITED_USE_CODE->{
				desription=String.format("Oluşturulan sınırsız kullanımlık kod:%s",createDiscountCodeInformation.getCode());

				totalCodeCount=1;
			}
			case SINGLE_USE_CODE ->{
				desription=String.format("Oluşturulan kod sayısı:%s",createDiscountCodeInformation.getCodeGenerationCount());
				totalCodeCount=createDiscountCodeInformation.getCodeGenerationCount();
			}
			default -> throw new CampaignManagementException("discount.code.type.not.found");
		}
		discountCodeInformationDetailEntity.setCodeCount(totalCodeCount);
		discountCodeInformationDetailEntity.setDescription(desription);
		discountCodeInformationDetailJpaRepository.save(discountCodeInformationDetailEntity);
	}

	@Async
	public void saveDiscountCodes(CreateDiscountCodeInformation createDiscountCodeInformation,
								  DiscountCodeInformation discountCodeInformation) {
		var discountCodeInformationEntity = discountCodeInformationJpaRepository
				.findById(discountCodeInformation.getId())
				.orElseThrow(()->new CampaignManagementException(ExceptionConstants.DISCOUNT_CODE_INFORMATION_NOT_FOUND));
		var generatedCodeSize=0;
		try {
			switch (CodeTypeEnum.of(discountCodeInformationEntity.getCodeType().getName())){
				case THIRD_PARTY_CODE ->{
					var currentCodeCount  =discountCodeJpaRepository
							.countByDiscountCodeInformationId(discountCodeInformationEntity.getId()).intValue();
					var  newCodeCount=saveThirdPartyDiscountCode(createDiscountCodeInformation, discountCodeInformationEntity)
							.size();
					generatedCodeSize=currentCodeCount+newCodeCount;
				}
				case UNLIMITED_USE_CODE->{
					saveUnlimitedCode(createDiscountCodeInformation,discountCodeInformationEntity);
					generatedCodeSize=1;
				}
				case SINGLE_USE_CODE -> {
					var currentCodeCount  =discountCodeJpaRepository
							.countByDiscountCodeInformationId(discountCodeInformationEntity.getId()).intValue();
					var newCodeCount=saveSingleUseGeneratedCode(createDiscountCodeInformation,discountCodeInformationEntity)
							.size();
					generatedCodeSize=currentCodeCount+newCodeCount;

				}
				default -> throw new CampaignManagementException("discount.code.type.not.found");
			}
			discountCodeInformationEntity.setUploadFileStatus(UploadFileEnum.UPLOADED);

			discountCodeInformationEntity.setCodeCount((long) generatedCodeSize);

		} catch (Exception e) {
			log.info("discount code exception ->" + e.getMessage());
			discountCodeInformationEntity.setUploadFileStatus(UploadFileEnum.NOT_UPLOADED);
		}finally {
			discountCodeInformationEntity.setUploadFinishTime(LocalDateTime.now());
			discountCodeInformationJpaRepository.save(discountCodeInformationEntity);
		}


	}

	private List<DiscountCode> saveThirdPartyDiscountCode(CreateDiscountCodeInformation createDiscountCodeInformation, DiscountCodeInformationEntity discountCodeInformationEntity) throws InterruptedException {
		var thirdPartyDiscountCodes = createDiscountCodeInformation.getDiscountCodes();

		List<List<DiscountCode>> subSets = Lists.partition(thirdPartyDiscountCodes,
				Math.min(thirdPartyDiscountCodes.size(), 10000));
		for(var set:subSets){
			saveThirdPartyDiscountCode(set, discountCodeInformationEntity);
		}

		 return thirdPartyDiscountCodes;
	}

	public void saveUnlimitedCode(CreateDiscountCodeInformation createDiscountCodeInformation,
								  DiscountCodeInformationEntity discountCodeInformationEntity){
			var definedCode = discountCodeJpaRepository
					.findByCodeAndIsActiveTrue(createDiscountCodeInformation.getCode()).orElse(null);
			if (definedCode == null) {
				DiscountCodeEntity discountCodeEntity = new DiscountCodeEntity();
				discountCodeEntity.setIsActive(Boolean.TRUE);
				discountCodeEntity.setCode(createDiscountCodeInformation.getCode());
				discountCodeEntity.setDiscountCodeInformation(discountCodeInformationEntity);

				discountCodeInformationEntity.setCodeCharacterLength((long)createDiscountCodeInformation.getCode().length());
				discountCodeInformationEntity.setCodeCount((long)1);
				discountCodeInformationJpaRepository.save(discountCodeInformationEntity);

				discountCodeJpaRepository.save(discountCodeEntity);
			} else {
				throw new CampaignManagementException("already.defined.unlimited.code");
			}

	}

	private Callable<String> saveThirdPartyDiscountCodes(List<DiscountCode> discountCodes,
														DiscountCodeInformationEntity discountCodeInformationEntity){
		return () -> {
			var codes= discountCodes.stream()
					.map(item -> DiscountCodeEntity.builder().code(item.getCode())
							.contactNumber(item.getCustomerNo())
							.isActive(Boolean.TRUE)
							.discountCodeInformation(discountCodeInformationEntity)
							.codeStatusEnum(
									item.getCustomerNo() != null ? CodeStatusEnum.PROPOSED : CodeStatusEnum.UNUSED)
							.build()).toList();

			log.info(String.format("Saved discount code size %s",codes));
			discountCodeJpaRepository.saveAll(codes);
			return "Task's execution";
		};
	}
	private void saveThirdPartyDiscountCode(List<DiscountCode> discountCodes,
														 DiscountCodeInformationEntity discountCodeInformationEntity){

			var codes= discountCodes.stream()
					.map(item -> DiscountCodeEntity.builder().code(item.getCode())
							.contactNumber(item.getCustomerNo())
							.isActive(Boolean.TRUE)
							.discountCodeInformation(discountCodeInformationEntity)
							.codeStatusEnum(
									item.getCustomerNo() != null ? CodeStatusEnum.PROPOSED : CodeStatusEnum.UNUSED)
							.build()).toList();

			log.info(String.format("Saved discount code size %s",codes));
			discountCodeJpaRepository.saveAll(codes);

	}
	 public List<DiscountCode> saveSingleUseGeneratedCode(CreateDiscountCodeInformation createDiscountCodeInformation,
			DiscountCodeInformationEntity codeInformation) {

		 var codes= mapThirdPartyDiscountCodesByGeneratedCodes(createDiscountCodeInformation);

		 ExecutorService executor = Executors.newSingleThreadExecutor();
		 executor.submit(saveThirdPartyDiscountCodes(codes,codeInformation));
		 return codes;
	 }

	private List<DiscountCode> mapThirdPartyDiscountCodesByGeneratedCodes(CreateDiscountCodeInformation createDiscountCodeInformation) {
		var generatedCodes=codeGenerator
				.generateCode(createDiscountCodeInformation.getCodeCharacterLength().intValue()
						, createDiscountCodeInformation.getCodeGenerationCount());
		var contacts= createDiscountCodeInformation.getDiscountCodes()!=null?new ArrayList<>(createDiscountCodeInformation
				.getDiscountCodes().stream()
				.map(DiscountCode::getCustomerNo)
				.toList()):null;
		var discountCodes=new ArrayList<DiscountCode>();
		for(var code:generatedCodes){
			var dCode= DiscountCode.builder().build();
			dCode.setCode(code);
			if(createDiscountCodeInformation.isPairedWithCustomers() && contacts!=null){
				var contactOptional=contacts.stream().findAny();
				if(contactOptional.isPresent()){
					dCode.setCustomerNo(contactOptional.get());
					contacts.remove(contactOptional.get());
				}
			}
			discountCodes.add(dCode);
		}
		return discountCodes;
	}


	@Override
	public boolean checkSaleStatusCodes(CheckSaleStatus checkSaleStatus) {
		var result = new AtomicBoolean(true);
		if(checkSaleStatus.getInsuredCheckStatus()!=null){
			checkSaleStatus.getInsuredCheckStatus().forEach(item->{
				var ruleGroup = campaignRuleGroupJpaRepository.findById(item.getRuleGroupId())
						.orElseThrow(() -> new CampaignManagementException("rule.group.not.found"));

				if(item.getCodeType()!=null && item.getCodeType().equals(SaleCodeTypeEnum.CODE)){
				var discountInformationId = ruleGroup.getReward().getDiscount().getDiscountCodeInformation().getId();
				var	code = discountCodeJpaRepository
							.findByDiscountCodeInformationIdAndCode(discountInformationId,
									item.getCampaignCode())
							.orElseThrow(() -> new CampaignManagementException(ExceptionConstants.CODE_NOT_FOUND,
									item.getContactNumber()));
				var codeType=code.getDiscountCodeInformation().getCodeType().getName();
				if(codeType.equals(CodeTypeEnum.SINGLE_USE_CODE.getValue())){
					var count=checkSaleStatus.getInsuredCheckStatus().stream()
							.filter(t->t.getCampaignCode().equals(item.getCampaignCode())).count();
					if(count>1){
						result.set(false);

					}
				}
				}
			});
		}

		return result.get();
	}


}