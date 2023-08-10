package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignCriteria;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.sale.model.*;
import com.anadolusigorta.campaignmanagement.domain.sale.port.SaleCampaignCriteriaRepository;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionOperationType;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.*;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.*;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SaleCampaignEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SaleReportViewEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository.SaleCampaignJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository.SaleReportViewJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SaleReportRepositoryJpaAdapter implements SaleCampaignCriteriaRepository {

    private final SaleReportViewJpaRepository saleReportViewJpaRepository;

	private final SaleCampaignJpaRepository saleCampaignJpaRepository;
	@Override
	public PageContent<SaleReport> findSaleCampaigns(SaleReportCriteria criteria, Pageable pageable) {
		var sales= saleReportViewJpaRepository
				.findAll(matchedCriteria(criteria),
										PageRequest.of(pageable.getPageNumber()-1
												,pageable.getPageSize()
												,pageable.getSort()));

		return PageContent.<SaleReport>builder()
				.content(sales.getContent().stream()
						.map(SaleReportViewEntity::toModel)
						.collect(Collectors.toList()))
				.page(pageable.getPageNumber())
				.size(pageable.getPageSize())
				.totalItems(sales.getTotalElements())
				.build();
	}

	@Override
	public List<SaleReport> findSaleCampaignInformation(SaleReportCriteria saleCampaignInformationCriteria) {
		return saleReportViewJpaRepository.findAll(matchedCriteria(saleCampaignInformationCriteria))
				.stream()
				.map(SaleReportViewEntity::toModel)
				.toList();
	}

	private Specification<SaleReportViewEntity> matchedCriteria(SaleReportCriteria saleReportCriteria) {


		return (root, query, builder) -> {

			List<Predicate> predicates = predicating(saleReportCriteria, root, builder);

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

	private static List<Predicate> predicating(SaleReportCriteria saleReportCriteria, Root<SaleReportViewEntity> root, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<>();
		if(saleReportCriteria.getCampaignId()!=null){
			final Path<Long> id=getPath(Long.class, root, "campaignId");
			predicates.add(builder.equal(id, saleReportCriteria.getCampaignId()));
		}
		if(saleReportCriteria.getContactNumber()!=null){
			final Path<String> id=getPath(String.class, root, "contactNumber");
			predicates.add(builder.like(id, "%"+ saleReportCriteria.getContactNumber()+"%"));
		}
		if(saleReportCriteria.getPolicyNumber()!=null){
			final Path<String> id=getPath(String.class, root, "policyNumber");
			predicates.add(builder.like(id, "%"+saleReportCriteria.getPolicyNumber()+"%"));
		}
		if(saleReportCriteria.getProposalNumber()!=null){
			final Path<String> id=getPath(String.class, root, "proposal");
			predicates.add(builder.like(id, "%"+ saleReportCriteria.getProposalNumber()+"%"));
		}
		if(saleReportCriteria.getCampaignVersion()!=null){
			final Path<Long> id=getPath(Long.class, root, "campaignVersion");
			predicates.add(builder.equal(id, saleReportCriteria.getCampaignVersion()));
		}
		if(saleReportCriteria.getRequestType()!=null){
			final Path<String> id=getPath(String.class, root, "requestType");
			predicates.add(builder.equal(id, saleReportCriteria.getRequestType()));
		}
		return predicates;
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

}
