package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.*;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignSearchStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.customercampaign.port.CustomerCampaignRepository;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.entity.AttributeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.*;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CustomerCampaignJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.Specification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerCampaignRepositoryJpaAdapter implements CustomerCampaignRepository {

    private final CustomerCampaignJpaRepository customerCampaignJpaRepository;

    static Specification<CustomerCampaignEntity> isCurrentDateAfterStartDate() {
        return (campaignInformation, cq, cb) -> cb.lessThan(getPath(LocalDateTime.class, campaignInformation,
                "campaignVersion.campaignInformation.campaignStartDate"), LocalDateTime.now());
    }

    static Specification<CustomerCampaignEntity> isCurrentDateBeforeStartDate() {
        return (campaignInformation, cq, cb) -> cb.greaterThan(getPath(LocalDateTime.class, campaignInformation,
                "campaignVersion.campaignInformation.campaignStartDate"), LocalDateTime.now());
    }

    static Specification<CustomerCampaignEntity> isCurrentDateAfterEndDate() {
        return (campaignInformation, cq, cb) -> cb.lessThan(getPath(LocalDateTime.class, campaignInformation,
                "campaignVersion.campaignInformation.campaignEndDate"), LocalDateTime.now());
    }


    static Specification<CustomerCampaignEntity> isCurrentDateBeforeEndDate() {
        return (campaignInformation, cq, cb) -> cb.greaterThan(getPath(LocalDateTime.class, campaignInformation,
                "campaignVersion.campaignInformation.campaignEndDate"), LocalDateTime.now());
    }

    static Specification<CustomerCampaignEntity> isCampaignStatusTypeEqual(CampaignStatusEnum statusType) {
        return (campaignInformation, cq, cb) -> cb.equal(getPath(Long.class, campaignInformation,
                "campaignVersion.campaignInformation.campaignStatus.campaignStatus"), statusType);
    }

    static Specification<CustomerCampaignEntity> campaignStatusTypeNotEqual(CampaignStatusEnum statusType) {
        return (campaignInformation, cq, cb) -> cb.notEqual(getPath(Long.class, campaignInformation,
                "campaignVersion.campaignInformation.campaignStatus.campaignStatus"), statusType);
    }

    @SuppressWarnings("unchecked")
    private static <T, R> Path<R> getPath(Class<R> resultType, Path<T> root, String path) {
        String[] pathElements = path.split("\\.");
        Path<?> retVal = root;
        for (String pathEl : pathElements) {
            retVal = (Path<R>) retVal.get(pathEl);
        }
        return (Path<R>) retVal;
    }

    @Override
    public List<Campaign> findAll() {
        return customerCampaignJpaRepository
                .findByCampaignVersionCampaignInformationCampaignStatusCampaignStatus(
                        CampaignStatusEnum.ACTIVE_CAMPAIGN)
                .stream()
                .map(CustomerCampaignEntity::toModel).
				filter(item -> item.getCampaignInformation().getCampaignEndDate().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @Override
    public Campaign findByCampaignId(Long campaignId) {
        return customerCampaignJpaRepository.findByCampaignId(campaignId)
                .orElseThrow(() -> new CampaignManagementException("campaign.not.found"))
                .toModel();

    }

    @Override
    public List<CampaignInformation> findContactCampaignsInformation(CampaignCriteria campaignCriteria) {
        var campaigns=customerCampaignJpaRepository
                .findAll(matchedCriteria(campaignCriteria));
        return campaigns
                .stream()
                .map(item -> item.getCampaignVersion().getCampaignInformation()
                        .toModel()).collect(Collectors.toList());
    }

    @Override
    public Campaign findByCampaignIdAndIsActiveTrue(Long campaignId) {
        return customerCampaignJpaRepository
                .findByCampaignIdAndCampaignVersionCampaignInformationCampaignStartDateLessThanAndCampaignVersionCampaignInformationCampaignEndDateGreaterThan(
                        campaignId, LocalDateTime.now(), LocalDateTime.now())
                .orElseThrow(() -> new CampaignManagementException("campaign.not.found")).toModel();
    }



    @Override
    public List<CampaignInformation> getDiscountCodeDefinedParticipantCampaigns() {
        return customerCampaignJpaRepository
                .findAll(where(isCampaignStatusTypeEqual(CampaignStatusEnum.ACTIVE_CAMPAIGN)
                        .and(isCurrentDateBeforeEndDate().and(isCurrentDateAfterStartDate()).and(isCampaignTypeEqual(3L)))))
                .stream()
                .filter(item -> discountCodeInformationExistAndNotUsed(item.getCampaignVersion().getCampaignRuleGroups()))
                .map(item -> item.getCampaignVersion().getCampaignInformation().toModel())
                .collect(Collectors.toList());
    }

	@Override
    public AvailableCampaign findAvailableCampaignByCampaignIdAndRuleGroupId(Long campaignId,
			Long ruleGroupId) {

        var customerCampaign=customerCampaignJpaRepository.findByCampaignId(campaignId)
                .orElseThrow(()->new CampaignManagementException("campaign.not.found"))
                .toModel();
        return AvailableCampaign.builder()
                .campaignInformation(customerCampaign.getCampaignInformation())
                .ruleGroup(customerCampaign.getRuleGroups()
                        .stream()
                        .filter(item-> item.getRuleGroupId().equals(ruleGroupId))
                        .findFirst()
                        .orElseThrow(()->new CampaignManagementException("rule.group.not.found")))
                .build();

	}



    @Override
    public List<CampaignInformation> findContactCampaignsInformation(String campaignStatusType, Long campaignTypeId) {
        return switch (CampaignSearchStatusEnum.of(campaignStatusType != null ? campaignStatusType : "")) {
            case ACTIVE_CAMPAIGNS ->//
                    customerCampaignJpaRepository
                            .findAll(where(isCurrentDateAfterStartDate().and(isCurrentDateBeforeEndDate()
                                    .and(isCampaignStatusTypeEqual(CampaignStatusEnum.ACTIVE_CAMPAIGN)
                                            .and(isCampaignTypeEqual(campaignTypeId))))))
                            .stream().map(item -> item.getCampaignVersion().getCampaignInformation().toModel())
                            .sorted(Comparator.comparingLong(CampaignInformation::getCampaignId))
                            .collect(Collectors.toList());
            case TERMINATED_CAMPAIGNS -> customerCampaignJpaRepository
                    .findAll(where((isCampaignStatusTypeEqual(CampaignStatusEnum.CLOSED_CAMPAIGN)
                            .or(isCurrentDateAfterEndDate())).and(isCampaignTypeEqual(campaignTypeId))))
                    .stream().map(item -> item.getCampaignVersion().getCampaignInformation().toModel())
                    .sorted(Comparator.comparingLong(CampaignInformation::getCampaignId))
                    .collect(Collectors.toList());
            case PENDING_CAMPAIGNS -> customerCampaignJpaRepository
                    .findAll(where(isCampaignStatusTypeEqual(CampaignStatusEnum.PENDING_CAMPAIGN)
                            .and(isCurrentDateBeforeEndDate())
                            .and(isCampaignTypeEqual(campaignTypeId))))
                    .stream().map(item -> item.getCampaignVersion().getCampaignInformation().toModel())
                    .sorted(Comparator.comparingLong(CampaignInformation::getCampaignId))
                    .collect(Collectors.toList());
            case FUTURE_CAMPAIGNS -> customerCampaignJpaRepository
                    .findAll(where(isCampaignStatusTypeEqual(CampaignStatusEnum.ACTIVE_CAMPAIGN)
                            .and(isCurrentDateBeforeStartDate().and(isCampaignTypeEqual(campaignTypeId)))))
                    .stream().map(item -> item.getCampaignVersion().getCampaignInformation().toModel())
                    .sorted(Comparator.comparingLong(CampaignInformation::getCampaignId))
                    .collect(Collectors.toList());
            default -> customerCampaignJpaRepository.findAll(where(isCampaignTypeEqual(campaignTypeId))).stream()
                    .map(item -> item.getCampaignVersion().getCampaignInformation().toModel())
                    .sorted(Comparator.comparingLong(CampaignInformation::getCampaignId))
                    .collect(Collectors.toList());
        };
    }

    @Override
    public PageContent<CampaignInformation> findContactCampaignsInformationPageable(CampaignCriteria campaignCriteria, Pageable pageable) {

        var campaigns=customerCampaignJpaRepository
                .findAll(matchedCriteria(campaignCriteria,pageable),PageRequest.of(pageable.getPageNumber()-1,pageable.getPageSize()));
        return PageContent.<CampaignInformation>builder()
                .content(campaigns.getContent()
                        .stream()
                        .map(item -> item.getCampaignVersion().getCampaignInformation()
                        .toModel()).collect(Collectors.toList()))
                .size(pageable.getPageSize())
                .page(pageable.getPageNumber())
                .totalItems(campaigns.getTotalElements())
                .build();
    }

    @Override
    public PageContent<CampaignVersion> findContactCampaignsVersionPageable(CampaignCriteria campaignCriteria, Pageable pageable) {
        var campaigns=customerCampaignJpaRepository
                .findAll(matchedCriteria(campaignCriteria,pageable),PageRequest.of(pageable.getPageNumber()-1,pageable.getPageSize()));
        return PageContent.<CampaignVersion>builder()
                .content(campaigns.getContent()
                        .stream()
                        .map(item -> item.getCampaignVersion()
                                .toExtendedModel())
                        .collect(Collectors.toList()))
                .size(pageable.getPageSize())
                .page(pageable.getPageNumber())
                .totalItems(campaigns.getTotalElements())
                .totalPages(campaigns.getTotalPages())
                .build();
    }


    private Specification<CustomerCampaignEntity> matchedCriteria(CampaignCriteria campaignCriteria,Pageable pageable) {


        return (root, query, builder) -> {

            List<Predicate> predicates = predicating(campaignCriteria, root, builder);

            sorting(pageable, root, query, builder);

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<CustomerCampaignEntity> matchedCriteria(CampaignCriteria campaignCriteria) {


        return (root, query, builder) -> {

            List<Predicate> predicates = predicating(campaignCriteria, root, builder);

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static List<Predicate> predicating(CampaignCriteria campaignCriteria, Root<CustomerCampaignEntity> root, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if(campaignCriteria.getCampaignId()!=null){
            final Path<Long> id=getPath(Long.class, root, "campaign.id");
            predicates.add(builder.equal(id, campaignCriteria.getCampaignId()));
        }

        if(campaignCriteria.getCampaignName()!=null && !campaignCriteria.getCampaignName().isEmpty()){
            final Path<String> name=getPath(String.class, root, "campaignVersion.campaignInformation.campaignName");
            predicates.add(builder.like(builder.lower(name),("%"+ campaignCriteria.getCampaignName()+"%").toLowerCase()));

        }

        if(campaignCriteria.getCampaignTypeId() != null ){
            final Path<Long> id=getPath(Long.class, root, "campaignVersion.campaignInformation.campaignType.id");
            predicates.add(builder.equal(id,campaignCriteria.getCampaignTypeId()));
        }

        if(campaignCriteria.getAttributeId()!=null){


            Join<CampaignVersionEntity, Set<CampaignRuleGroupEntity>> campaignVersions = root.join("campaignVersion", JoinType.INNER);
            Join<CampaignRuleGroupEntity, Set<CampaignRuleEntity>> campaignRuleGroups = campaignVersions.join("campaignRuleGroups", JoinType.INNER);

            Join<CampaignRuleEntity, CampaignAttributeEntity> campaignRule = campaignRuleGroups.join("campaignRules");
            Join<CampaignAttributeEntity, AttributeEntity> attributeJoin = campaignRule.join("campaignAttribute");

            Path<Long> attributeIdPath = attributeJoin.get("attribute").get("id");
            predicates.add(builder.equal(attributeIdPath, campaignCriteria.getAttributeId()));

            Path<String> attributeValuePath = campaignRule.get("value");

            if(campaignCriteria.getOperator().equals(OperatorEnum.EQ)){
                predicates.add(builder.equal(attributeValuePath, campaignCriteria.getValue().get(0)));

            } else if (campaignCriteria.getOperator().equals(OperatorEnum.NEQ)) {
                predicates.add(builder.notEqual(attributeValuePath, campaignCriteria.getValue().get(0)));

            }
            else if (campaignCriteria.getOperator().equals(OperatorEnum.IN)) {
                predicates.add(attributeValuePath.in(campaignCriteria.getValue()));

            }
            else if (campaignCriteria.getOperator().equals(OperatorEnum.NIN)) {
                predicates.add(builder.not(attributeValuePath.in(campaignCriteria.getValue())));

            }

            else if (campaignCriteria.getOperator().equals(OperatorEnum.GT)) {
                predicates.add(builder.greaterThan(attributeValuePath, campaignCriteria.getValue().get(0)));

            }


        }

        if(campaignCriteria.getCampaignStatusType() != null){
            if(campaignCriteria.getCampaignStatusType().equals(CampaignSearchStatusEnum.ACTIVE_CAMPAIGNS.getValue())){
                final Path<String> name=getPath(String.class, root, "campaignVersion.campaignInformation.campaignStatus.campaignStatus");
                final Path<LocalDateTime> startDate=getPath(LocalDateTime.class, root, "campaignVersion.campaignInformation.campaignStartDate");
                final Path<LocalDateTime> endDate=getPath(LocalDateTime.class, root, "campaignVersion.campaignInformation.campaignEndDate");
                predicates.add(builder.equal(name,CampaignStatusEnum.ACTIVE_CAMPAIGN));
                predicates.add(builder.lessThanOrEqualTo(startDate,LocalDateTime.now()));
                predicates.add(builder.greaterThanOrEqualTo(endDate,LocalDateTime.now()));
            }

            if(campaignCriteria.getCampaignStatusType().equals(CampaignSearchStatusEnum.FUTURE_CAMPAIGNS.getValue())){
                final Path<String> name=getPath(String.class, root, "campaignVersion.campaignInformation.campaignStatus.campaignStatus");
                final Path<LocalDateTime> startDate=getPath(LocalDateTime.class, root, "campaignVersion.campaignInformation.campaignStartDate");
                predicates.add(builder.equal(name,CampaignStatusEnum.ACTIVE_CAMPAIGN));
                predicates.add(builder.greaterThan(startDate,LocalDateTime.now()));
            }

            if(campaignCriteria.getCampaignStatusType().equals(CampaignSearchStatusEnum.PENDING_CAMPAIGNS.getValue())){
                final Path<String> name=getPath(String.class, root, "campaignVersion.campaignInformation.campaignStatus.campaignStatus");
                predicates.add(builder.equal(name,CampaignStatusEnum.PENDING_CAMPAIGN));
            }

            if(campaignCriteria.getCampaignStatusType().equals(CampaignSearchStatusEnum.TERMINATED_CAMPAIGNS.getValue())){
               final Path<LocalDateTime> endDate=getPath(LocalDateTime.class, root, "campaignVersion.campaignInformation.campaignStartDate");
                predicates.add(builder.lessThan(endDate,LocalDateTime.now()));
            }
        }
        return predicates;
    }

    private static void sorting(Pageable pageable, Root<CustomerCampaignEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        var sort= pageable.getSort().get().findFirst();
        if(sort.isPresent()){
            var sortName=sort.get().getProperty();
            var direction=sort.get().getDirection();
            var path = switch (sortName) {
                case "campaignId" -> "campaign.id";
                case "createDate" -> "createdAt";
                case "version" -> "campaignVersion.version";
                case "campaignGroupName" -> "campaignVersion.campaignInformation.campaignGroup.name";
                case "campaignTypeName" -> "campaignVersion.campaignInformation.campaignType.name";
                case "campaignStatusName" -> "campaignVersion.campaignInformation.campaignStatus.name";
                default -> "campaignVersion.campaignInformation." + sortName;
            };
            final Path<String> name=getPath(String.class, root, path);
            if(direction.equals(Sort.Direction.ASC)){
                query.orderBy(builder.asc(name));

            }else {
                query.orderBy(builder.desc(name));

            }

        }else {
            final Path<String> name=getPath(String.class, root, "createdAt");
            query.orderBy(builder.desc(name));

        }
    }

    private Specification<CustomerCampaignEntity> isCampaignTypeEqual(Long campaignTypeId) {
        if (campaignTypeId != null) {
            return (campaignInformation, cq, cb) -> cb.equal(
                    getPath(Long.class, campaignInformation, "campaignVersion.campaignInformation.campaignType.id"),
                    campaignTypeId);
        }
        return null;
    }

    private Boolean discountCodeInformationExistAndNotUsed(Set<CampaignRuleGroupEntity> campaignRuleGroups){
       return campaignRuleGroups.stream().anyMatch(item ->
            item.getReward() != null
                    && item.getReward().getDiscount() != null
                    && item.getReward().getDiscount().getDiscountCodeInformation() != null
                    && !item.getReward().getDiscount().getDiscountCodeInformation().getIsUsed()
        );
    }

}
