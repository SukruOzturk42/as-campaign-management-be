package com.anadolusigorta.campaignmanagement.infrastructure.recordtracking.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.recordtracking.model.SaleProcessCriteria;
import com.anadolusigorta.campaignmanagement.domain.recordtracking.port.SalesProcessRepository;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleOperationsRequests;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.Specification;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.CheckCodeRequestEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.FindSaleCampaignsRequestEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SaleCampaignRequestEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository.CheckCodeRequestJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository.SaleCampaignRequestJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository.SaleFindCampaignsRequestJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class SalesProcessJpaRepositoryAdapter implements SalesProcessRepository {

    private final SaleFindCampaignsRequestJpaRepository saleFindCampaignsRequestJpaRepository;

    private final CheckCodeRequestJpaRepository checkCodeRequestJpaRepository;

    private final SaleCampaignRequestJpaRepository saleCampaignRequestJpaRepository;

    private static final String transactionIdConst = "transactionId";

    private static final String contactNumberConst = "contactNumber";

    private static final String transactionalIdConst = "transactionalId";

    private static final String createdAtConst = "createdAt";


    @Override
    public PageContent<SaleOperationsRequests> findAllSaleOperationsRequestPageable(SaleProcessCriteria saleProcessCriteria, Pageable pageable) {

        var saleProcesses= saleFindCampaignsRequestJpaRepository.findAll(matchedCriteriaFind(saleProcessCriteria,pageable),
                PageRequest.of(pageable.getPageNumber()-1,
                        pageable.getPageSize()));

        var content = saleProcesses
                .getContent()
                .stream()
                .map(FindSaleCampaignsRequestEntity::toModel)
                .collect(Collectors.toList());

        return PageContent.<SaleOperationsRequests>builder()
                .content(content)
                .size(pageable.getPageSize())
                .page(pageable.getPageNumber())
                .totalItems(saleProcesses.getTotalElements())
                .build();
    }

    @Override
    public PageContent<SaleOperationsRequests> findAllCheckRequestPageable(SaleProcessCriteria saleProcessCriteria, Pageable pageable) {
        var checkProcesses= checkCodeRequestJpaRepository.findAll(matchedCriteriaCheck(saleProcessCriteria,pageable), PageRequest.of(pageable.getPageNumber() -1 ,pageable.getPageSize()));

        var content = checkProcesses
                .getContent()
                .stream()
                .map(CheckCodeRequestEntity::toModel)
                .collect(Collectors.toList());

        return PageContent.<SaleOperationsRequests>builder()
                .content(content)
                .size(pageable.getPageSize())
                .page(pageable.getPageNumber())
                .totalItems(checkProcesses.getTotalElements())
                .build();
    }

    @Override
    public PageContent<SaleOperationsRequests> findAllNotifyRequestPageable(SaleProcessCriteria saleProcessCriteria, Pageable pageable) {
        var notifyProcesses= saleCampaignRequestJpaRepository.findAll(matchedCriteriaNotify(saleProcessCriteria,pageable), PageRequest.of(pageable.getPageNumber() -1 ,pageable.getPageSize()));

        var content = notifyProcesses
                .getContent()
                .stream()
                .map(SaleCampaignRequestEntity::toModel)
                .collect(Collectors.toList());

        return PageContent.<SaleOperationsRequests>builder()
                .content(content)
                .size(pageable.getPageSize())
                .page(pageable.getPageNumber())
                .totalItems(notifyProcesses.getTotalElements())
                .build();
    }



    private Specification<FindSaleCampaignsRequestEntity> matchedCriteriaFind(SaleProcessCriteria saleProcessCriteria, Pageable pageable) {

        return (root, query, builder) -> {

            List<Predicate> predicates = predicatingFind(saleProcessCriteria, root, builder);

            sortingFind(pageable, root, query, builder);

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<CheckCodeRequestEntity> matchedCriteriaCheck(SaleProcessCriteria saleProcessCriteria, Pageable pageable) {

        return (root, query, builder) -> {

            List<Predicate> predicates = predicatingCheck(saleProcessCriteria, root, builder);

            sortingCheck(pageable, root, query, builder);

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<SaleCampaignRequestEntity> matchedCriteriaNotify(SaleProcessCriteria saleProcessCriteria, Pageable pageable) {

        return (root, query, builder) -> {

            List<Predicate> predicates = predicatingNotify(saleProcessCriteria, root, builder);

            sortingNotify(pageable, root, query, builder);

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static List<Predicate> predicatingFind(SaleProcessCriteria saleProcessCriteria, Root<FindSaleCampaignsRequestEntity> root, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if(saleProcessCriteria.getTransactionId()!=null && !saleProcessCriteria.getTransactionId().isEmpty()){
            final Path<String> transactionId=getPath(String.class, root, transactionIdConst);
            predicates.add(builder.like(transactionId, "%"+ saleProcessCriteria.getTransactionId() + "%"));
        }

        if(saleProcessCriteria.getContactNo()!=null && !saleProcessCriteria.getContactNo().isEmpty()){
            final Path<String> contactNo=getPath(String.class, root, contactNumberConst);
            predicates.add(builder.like(contactNo,"%" + saleProcessCriteria.getContactNo()+ "%"));

        }
        return predicates;
    }

    private static List<Predicate> predicatingCheck(SaleProcessCriteria saleProcessCriteria, Root<CheckCodeRequestEntity> root, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if(saleProcessCriteria.getTransactionId()!=null && !saleProcessCriteria.getTransactionId().isEmpty()){
            final Path<String> transactionId=getPath(String.class, root, transactionIdConst);
            predicates.add(builder.like(transactionId, "%"+ saleProcessCriteria.getTransactionId() + "%"));
        }

        if(saleProcessCriteria.getContactNo()!=null && !saleProcessCriteria.getContactNo().isEmpty()){
            final Path<String> contactNo=getPath(String.class, root, contactNumberConst);
            predicates.add(builder.like(contactNo,"%" + saleProcessCriteria.getContactNo()+ "%"));

        }
        return predicates;
    }

    private static List<Predicate> predicatingNotify(SaleProcessCriteria saleProcessCriteria, Root<SaleCampaignRequestEntity> root, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if(saleProcessCriteria.getTransactionId()!=null && !saleProcessCriteria.getTransactionId().isEmpty()){
            final Path<String> transactionId=getPath(String.class, root, transactionalIdConst);
            predicates.add(builder.like(transactionId, "%"+ saleProcessCriteria.getTransactionId() + "%"));
        }

        if(saleProcessCriteria.getContactNo()!=null && !saleProcessCriteria.getContactNo().isEmpty()){
            final Path<String> contactNo=getPath(String.class, root, contactNumberConst);
            predicates.add(builder.like(contactNo,"%" + saleProcessCriteria.getContactNo()+ "%"));

        }
        return predicates;
    }


    private static void sortingFind(Pageable pageable, Root<FindSaleCampaignsRequestEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
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

    private static void sortingCheck(Pageable pageable, Root<CheckCodeRequestEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
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

    private static void sortingNotify(Pageable pageable, Root<SaleCampaignRequestEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
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
