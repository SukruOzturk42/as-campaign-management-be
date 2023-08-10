package com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.ApprovalCampaignCriteria;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignCriteria;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignVersion;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.*;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.port.CampaignSearchRepository;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.entity.AttributeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.*;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignInformationJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.Specification;
import com.anadolusigorta.campaignmanagement.infrastructure.campaignstatus.rest.dto.request.CampaignStatusSearchRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.entity.ContactGroupEntity;
import lombok.RequiredArgsConstructor;
import org.mockito.cglib.core.Local;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
public class CampaignSearchRepositoryJpaAdapter implements CampaignSearchRepository {
    private final CampaignInformationJpaRepository campaignInformationJpaRepository;

    private static final String CAMPAIGN_STATUS = "campaignStatus.campaignStatus";

    private static final String CAMPAIGN_APPROVAL_STATUS= "campaignApprovalStatus.campaignApprovalStatus";

    static Specification<CampaignInformationEntity> isCampaignStatusTypeEqual(CampaignStatusEnum statusType) {
        return (campaignInformation, cq, cb) ->
                cb.equal(getPath(String.class, campaignInformation, CAMPAIGN_STATUS), statusType);
    }

    static Specification<CampaignInformationEntity> isActiveVersion() {
        return (campaignInformation, cq, cb) ->
                cb.equal(getPath(Long.class, campaignInformation, "campaignVersion.isActiveVersion"), 1);
    }

    private Specification<CampaignInformationEntity> isCampaignTypeEqual(Long campaignTypeId) {
        if (campaignTypeId != null) {
            return (campaignInformation, cq, cb) ->
                    cb.equal(getPath(Long.class, campaignInformation, "campaignType.id"), campaignTypeId);
        }
        return null;
    }

    static Specification<CampaignInformationEntity> isCampaignApprovalStatusNameEqual(CampaignApprovalStatusEnum statusType) {
        return (campaignInformation, cq, cb) ->
                cb.equal(getPath(String.class, campaignInformation, CAMPAIGN_APPROVAL_STATUS), statusType);
    }

    static Specification<CampaignInformationEntity> isCampaignStatusTypeEqualNotEqual(CampaignStatusEnum statusType) {
        return (campaignInformation, cq, cb) ->
                cb.notEqual(getPath(String.class, campaignInformation, CAMPAIGN_STATUS), statusType);
    }
    static Specification<CampaignInformationEntity> isCurrentDateBeforeEndDate() {
        return (campaignInformation, cq, cb) -> cb.greaterThan(getPath(LocalDateTime.class, campaignInformation,
                "campaignVersion.campaignInformation.campaignEndDate"), LocalDateTime.now());
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
    public PageContent<CampaignInformation> getPageableContactCampaignsInformation(CampaignCriteria approvalCampaignCriteria, Pageable pageable) {
        var campaigns=campaignInformationJpaRepository
                .findAll(matchedCriteria(approvalCampaignCriteria,pageable),PageRequest.of(pageable.getPageNumber()-1,pageable.getPageSize()));
        return PageContent.<CampaignInformation>builder()
                .content(campaigns.getContent()
                        .stream()
                        .map(CampaignInformationEntity::toModel)
                        .collect(Collectors.toList()))
                .size(pageable.getPageSize())
                .page(pageable.getPageNumber())
                .totalItems(campaigns.getTotalElements())
                .build();
    }

    @Override
    public PageContent<CampaignVersion> findContactCampaignsVersionPageable(CampaignCriteria campaignCriteria, Pageable pageable) {
        var campaigns=campaignInformationJpaRepository
                .findAll(matchedCriteria(campaignCriteria, pageable),PageRequest.of(pageable.getPageNumber()-1,pageable.getPageSize()));
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

    @Override
    public List<CampaignInformation> getContactCampaignsInformation(String campaignStatusType, Long campaignTypeId) {
        return switch (CampaignSearchApprovalEnum.of(campaignStatusType != null ? campaignStatusType : "")) {
            case PENDING_CAMPAIGNS -> campaignInformationJpaRepository.findAll(where(isActiveVersion()
                            .and(isCampaignStatusTypeEqual(CampaignStatusEnum.TEMPLATE_CAMPAIGN)
                                    .and(isCampaignApprovalStatusNameEqual(CampaignApprovalStatusEnum.SENT_FOR_APPROVAL_CAMPAIGN)
                                            .and(isCampaignTypeEqual(campaignTypeId)

                                            ))
                                    .and(isCurrentDateBeforeEndDate())
                            )
                    )).stream()
                    .map(CampaignInformationEntity::toModel)
                    .sorted(Comparator.comparingLong(CampaignInformation::getCampaignId))
                    .collect(Collectors.toList());
            case REJECTED_CAMPAIGNS -> campaignInformationJpaRepository.
                    findAll(where(isActiveVersion()
                            .and(isCampaignApprovalStatusNameEqual(CampaignApprovalStatusEnum.REJECTED_CAMPAIGN)
                                    .and(isCampaignTypeEqual(campaignTypeId))
                            )
                            .and(isCurrentDateBeforeEndDate())
                    )).stream()
                    .map(CampaignInformationEntity::toModel)
                    .sorted(Comparator.comparingLong(CampaignInformation::getCampaignId))
                    .collect(Collectors.toList());
            case ACTION_NEEDED_CAMPAIGNS -> campaignInformationJpaRepository.
                    findAll(where(isActiveVersion()
                            .and(isCampaignApprovalStatusNameEqual(CampaignApprovalStatusEnum.TEMPLATE_CAMPAIGN)
                                    .and(isCampaignStatusTypeEqualNotEqual(CampaignStatusEnum.CLOSED_CAMPAIGN)
                                            .and(isCampaignTypeEqual(campaignTypeId)
                                            ))
                                    .and(isCurrentDateBeforeEndDate())

                            ))).stream()
                    .map(CampaignInformationEntity::toModel)
                    .sorted(Comparator.comparingLong(CampaignInformation::getCampaignId))
                    .collect(Collectors.toList());
            default -> campaignInformationJpaRepository.findAll(where(isActiveVersion()
                            .and(isCampaignTypeEqual(campaignTypeId)).and(isCurrentDateBeforeEndDate())
                    )).stream()
                    .map(CampaignInformationEntity::toModel)
                    .sorted(Comparator.comparingLong(CampaignInformation::getCampaignId))
                    .collect(Collectors.toList());
        };

    }

    private Specification<CampaignInformationEntity> matchedCriteria(CampaignCriteria approvalCampaignCriteria,
                                                                     Pageable pageable) {

        return (root, query, builder) -> {

            List<Predicate> predicates = predicating(approvalCampaignCriteria, root, builder);

            sorting(pageable, root, query, builder);

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static void sorting(Pageable pageable, Root<CampaignInformationEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
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

    private static List<Predicate> predicating(CampaignCriteria approvalCampaignCriteria, Root<CampaignInformationEntity> root, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        var campaignTypeId= approvalCampaignCriteria.getCampaignTypeId();
        var campaignStatusType = approvalCampaignCriteria.getCampaignStatusType();

        if(approvalCampaignCriteria.getCampaignId() != null ){
            final Path<Long> id=getPath(Long.class, root, "campaign.id");
            predicates.add(builder.equal(id,approvalCampaignCriteria.getCampaignId()));
        }

        if(approvalCampaignCriteria.getCampaignName() != null ){
            final Path<String> id=getPath(String.class, root, "campaignName");
            predicates.add(builder.like(id,"%" + approvalCampaignCriteria.getCampaignName() + "%"));
        }

        if(campaignTypeId != null ){
            final Path<Long> id=getPath(Long.class, root, "campaignType.id");
            predicates.add(builder.equal(id,campaignTypeId));
        }
        if(approvalCampaignCriteria.getAttributeId()!=null){

            Join<CampaignVersionEntity, Set<CampaignRuleGroupEntity>> campaignVersions = root.join("campaignVersion", JoinType.INNER);
            Join<CampaignRuleGroupEntity, Set<CampaignRuleEntity>> campaignRuleGroups = campaignVersions.join("campaignRuleGroups", JoinType.INNER);

            Join<CampaignRuleEntity, CampaignAttributeEntity> campaignRule = campaignRuleGroups.join("campaignRules");
            Join<CampaignAttributeEntity, AttributeEntity> attributeJoin = campaignRule.join("campaignAttribute");

            Path<Long> attributeIdPath = attributeJoin.get("attribute").get("id");
            predicates.add(builder.equal(attributeIdPath, approvalCampaignCriteria.getAttributeId()));

            Path<String> attributeValuePath = campaignRule.get("value");

            if(approvalCampaignCriteria.getOperator().equals(OperatorEnum.EQ)){
                predicates.add(builder.equal(attributeValuePath, approvalCampaignCriteria.getValue().get(0)));

            } else if (approvalCampaignCriteria.getOperator().equals(OperatorEnum.NEQ)) {
                predicates.add(builder.notEqual(attributeValuePath, approvalCampaignCriteria.getValue().get(0)));

            }
            else if (approvalCampaignCriteria.getOperator().equals(OperatorEnum.IN)) {
                predicates.add(attributeValuePath.in(approvalCampaignCriteria.getValue()));

            }
            else if (approvalCampaignCriteria.getOperator().equals(OperatorEnum.NIN)) {
                predicates.add(builder.not(attributeValuePath.in(approvalCampaignCriteria.getValue())));

            }


        }

        if(campaignStatusType != null){
            if(campaignStatusType.equals(CampaignSearchApprovalEnum.PENDING_CAMPAIGNS.getValue())){
                final Path<String> status=getPath(String.class, root, CAMPAIGN_STATUS);
                final Path<String> approvalStatus=getPath(String.class, root, CAMPAIGN_APPROVAL_STATUS);
                predicates.add(builder.equal(status,CampaignStatusEnum.TEMPLATE_CAMPAIGN));
                predicates.add(builder.equal(approvalStatus,CampaignApprovalStatusEnum.SENT_FOR_APPROVAL_CAMPAIGN));
            }

            if(campaignStatusType.equals(CampaignSearchApprovalEnum.REJECTED_CAMPAIGNS.getValue())){
                final Path<String> name=getPath(String.class, root, CAMPAIGN_APPROVAL_STATUS);
                predicates.add(builder.equal(name,CampaignApprovalStatusEnum.REJECTED_CAMPAIGN));
            }

            if(campaignStatusType.equals(CampaignSearchApprovalEnum.ACTION_NEEDED_CAMPAIGNS.getValue())){
                final Path<String> status=getPath(String.class, root, CAMPAIGN_STATUS);
                final Path<String> approvalStatus=getPath(String.class, root, CAMPAIGN_APPROVAL_STATUS);
                predicates.add(builder.notEqual(status,CampaignStatusEnum.CLOSED_CAMPAIGN));
                predicates.add(builder.equal(approvalStatus,CampaignApprovalStatusEnum.TEMPLATE_CAMPAIGN));
            }
        }

        final Path<LocalDateTime> date=getPath(LocalDateTime.class, root, "campaignVersion.campaignInformation.campaignEndDate");
        predicates.add(builder.greaterThan(date,LocalDateTime.now()));

        final Path<Long> version=getPath(Long.class, root, "campaignVersion.isActiveVersion");
        predicates.add(builder.equal(version,1));

        return predicates;
    }


}
