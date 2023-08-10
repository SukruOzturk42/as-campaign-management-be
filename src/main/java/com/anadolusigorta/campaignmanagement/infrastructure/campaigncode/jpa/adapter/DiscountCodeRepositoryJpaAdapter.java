package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeCriteria;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCode;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCode;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.port.CodeRepository;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.CmTask;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskCriteria;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.DiscountCodeInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.Specification;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.codegenerator.CodeConfig;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.codegenerator.CodeGenerator;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.DiscountCodeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.GiftCodeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository.DiscountCodeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository.GiftCodeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscountCodeRepositoryJpaAdapter implements CodeRepository {

    private final DiscountCodeJpaRepository discountCodeJpaRepository;
    private final GiftCodeJpaRepository giftCodeJpaRepository;
    private final CodeGenerator codeGenerator;

    public void generateCodes(DiscountCodeInformationEntity discountCodeInformationEntity, Long length, Long size) {
        CodeConfig codeConfig = new CodeConfig(length.intValue(), null, null, null, null);
        List<String> codes = new ArrayList<>();
        String code;

        if (discountCodeInformationEntity.getCodeType().getName().equals("SINGLE_USE_CODE")) {
            for (int i = 0; i < size; i++) {
                code = codeGenerator.generate(codeConfig);
                if(!codes.contains(code)) {
                    codes.add(code);
                    discountCodeJpaRepository.save(DiscountCodeEntity.builder()
                            .discountCodeInformation(discountCodeInformationEntity)
                            .code(code)
                            .isActive(Boolean.TRUE)
                            .codeStatusEnum(CodeStatusEnum.UNUSED)
                            .build());
                } else {
                    size+=1;
                }
            }
        } else {
            code = codeGenerator.generate(codeConfig);
            discountCodeJpaRepository.save(DiscountCodeEntity.builder()
                    .discountCodeInformation(discountCodeInformationEntity)
                    .code(code)
                    .isActive(Boolean.TRUE)
                    .build());
        }
    }


    @Override
    public PageContent<DiscountCode> findDiscountCodesByCriteria(CodeCriteria codeCriteria, Pageable pageable) {
        var discountCodes= discountCodeJpaRepository.findAll(matchedCriteria(codeCriteria),
                PageRequest.of(pageable.getPageNumber() - 1
                , pageable.getPageSize()
                , pageable.getSort()));
        return PageContent.<DiscountCode>builder()
                .content(discountCodes.getContent().stream()
                        .map(DiscountCodeEntity::toModel)
                        .collect(Collectors.toList()))
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .totalItems(discountCodes.getTotalElements())
                .build();
    }

    @Override
    public PageContent<GiftCode> findGiftCodesByCriteria(CodeCriteria codeCriteria, Pageable pageable) {
        var giftCodes = giftCodeJpaRepository.findAll(matchedCriteriaForGiftCode(codeCriteria),
                PageRequest.of(pageable.getPageNumber() - 1
                        , pageable.getPageSize()
                        , pageable.getSort()));
        return PageContent.<GiftCode>builder()
                .content(giftCodes.getContent().stream()
                        .map(GiftCodeEntity::toModel)
                        .collect(Collectors.toList()))
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .totalItems(giftCodes.getTotalElements())
                .build();
    }

    public static Specification<DiscountCodeEntity> matchedCriteria(CodeCriteria criteria) {
        return (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getCode() != null && !Objects.equals(criteria.getCode(), "")) {
                final Path<String> group = getPath(String.class, root, "code");
                predicates.add(builder.like(group, criteria.getCode() + "%"));
            }

            if (criteria.getContactNumber() != null && !Objects.equals(criteria.getContactNumber(), "")) {
                final Path<String> group = getPath(String.class, root, "contactNumber");
                predicates.add(builder.like(group, criteria.getContactNumber() + "%"));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<GiftCodeEntity> matchedCriteriaForGiftCode(CodeCriteria criteria) {
        return (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getCode() != null && !Objects.equals(criteria.getCode(), "")) {
                final Path<String> group = getPath(String.class, root, "code");
                predicates.add(builder.like(group, criteria.getCode() + "%"));
            }

            if (criteria.getContactNumber() != null && !Objects.equals(criteria.getContactNumber(), "")) {
                final Path<String> group = getPath(String.class, root, "contactNumber");
                predicates.add(builder.like(group, criteria.getContactNumber() + "%"));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
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
