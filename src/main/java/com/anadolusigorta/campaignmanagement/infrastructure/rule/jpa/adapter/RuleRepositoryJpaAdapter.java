package com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.rule.model.CreateRule;
import com.anadolusigorta.campaignmanagement.domain.rule.model.RuleCriteria;
import com.anadolusigorta.campaignmanagement.domain.rule.port.RuleRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.Specification;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity.RuleCampaignAttributeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.entity.RuleEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.rule.jpa.repository.RuleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class RuleRepositoryJpaAdapter implements RuleRepository {
    private final RuleJpaRepository ruleJpaRepository;
    private final CombinedRuleRelationRepositoryJpaAdapter combinedRuleRelationRepositoryJpaAdapter;
    private final RuleCampaignAttributeRepositoryJpaAdapter ruleCampaignAttributeRepositoryJpaAdapter;
    private final CampaignTypeJpaRepository campaignTypeJpaRepository;

    private static  final String ruleIdConst = "id";

    private static final String ruleNameConst = "name";

    private static final String createdAtConst = "createdAt";

    @Override
    public void saveRule(CreateRule createRule) {
        createRule.getRuleGroups().forEach(item -> {
            var ruleEntity = saveRuleEntity();
            ruleEntity.setName(item.getName());
            ruleEntity.setConjunctionOperator(item.getConjunctionOperator());
            ruleEntity.setCampaignType(campaignTypeJpaRepository.getOne(item.getCampaignTypeId()));
            item.getAttributes().forEach(attributeItem -> {
                if(attributeItem.getType().getValue().equals("RULE")) {
                    combinedRuleRelationRepositoryJpaAdapter.saveCombinedRuleRelation(ruleEntity,
                            ruleJpaRepository.getOne(attributeItem.getAttributeId()));
                } else {
                    ruleCampaignAttributeRepositoryJpaAdapter.saveRuleCampaignAttribute(attributeItem,item.getCampaignTypeId(), ruleEntity);
                }
            });
            ruleJpaRepository.save(ruleEntity);
        });
    }

    @Override
    public List<RuleEntity> getRules() {
        return ruleJpaRepository.findAllByOrderByCreatedAtAsc();
    }


    @Override
    public PageContent<RuleEntity> getPageableRules(RuleCriteria ruleCriteria, Pageable pageable) {

        var rule= ruleJpaRepository.findAll(matchedCriteria(ruleCriteria,pageable), PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize()));

        return PageContent.<RuleEntity>builder()
                .content(rule.getContent())
                .size(pageable.getPageSize())
                .page(pageable.getPageNumber())
                .totalItems(rule.getTotalElements())
                .build();
    }

    @Override
    public List<RuleEntity> getRulesByCampaignTypeId(Long campaignTypeId) {
        return ruleJpaRepository.findAllByCampaignTypeId(campaignTypeId);
    }

    @Override
    public RuleEntity getRuleByRuleId(Long ruleId) {
        var rules = ruleJpaRepository.findById(ruleId).orElseThrow(() -> new CampaignManagementException("rule.not.found"));
        if(rules.getRuleAttributes() != null && rules.getRuleAttributes().size() > 1){
            var sortedListOfRules = rules.getRuleAttributes()
                    .stream()
                    .sorted(Comparator.comparing(RuleCampaignAttributeEntity::getId))
                    .collect(Collectors.toList());
            Set<RuleCampaignAttributeEntity> sortedSetOfRules = new LinkedHashSet<>(sortedListOfRules);
            rules.setRuleAttributes(sortedSetOfRules);
        }
        return rules;
    }

    private RuleEntity saveRuleEntity() {

        RuleEntity ruleEntity = new RuleEntity();
        return ruleJpaRepository.save(ruleEntity);

    }

    private Specification<RuleEntity> matchedCriteria(RuleCriteria createRuleCriteria, Pageable pageable) {

        return (root, query, builder) -> {

            List<Predicate> predicates = predicating(createRuleCriteria, root, builder);

            sorting(pageable, root, query, builder);

            return builder.and(predicates.toArray(new Predicate[0]));

        };
    }

    private static List<Predicate> predicating(RuleCriteria ruleCriteria, Root<RuleEntity> root, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if(ruleCriteria.getRuleId()!=null){
            final Path<Long> ruleId=getPath(Long.class, root, ruleIdConst);
            predicates.add(ruleId.in(ruleCriteria.getRuleId()));
        }

        if(ruleCriteria.getRuleName()!=null && !ruleCriteria.getRuleName().isEmpty()){
            final Path<String> ruleName=getPath(String.class, root, ruleNameConst);
            predicates.add(builder.like(ruleName,"%" + ruleCriteria.getRuleName()+ "%"));

        }
        return predicates;
    }


    private static void sorting(Pageable pageable, Root<RuleEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        var sort= pageable.getSort().get().findFirst();
        if(sort.isPresent()){
            var sortName=sort.get().getProperty();
            var direction=sort.get().getDirection();

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
}
