/* dks20165 created on 13.09.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.adapter */

package com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignCriteria;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignInformation;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeCriteria;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.DiscountCode;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignSearchStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.campaignstatus.model.CampaignStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.contact.model.Contact;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactGroup;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactGroupCriteria;
import com.anadolusigorta.campaignmanagement.domain.contact.model.CreateContactGroup;
import com.anadolusigorta.campaignmanagement.domain.contact.port.ContactGroupRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignRuleGroupEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CustomerCampaignEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignRuleGroupJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.Specification;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response.CampaignInformationResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.DiscountCodeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.entity.ContactEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.entity.ContactGroupEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.jpa.ContactGroupJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.jpa.ContactJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.fileoperation.model.ExcelContact;
import com.anadolusigorta.campaignmanagement.infrastructure.fileoperation.parser.ExcelParser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactGroupJpaAdapter implements ContactGroupRepository {

	private final ExcelParser excelParser;
	private final ContactGroupJpaRepository contactGroupJpaRepository;
	private final ContactJpaRepository contactJpaRepository;
	private final CampaignRuleGroupJpaRepository campaignRuleGroupJpaRepository;

	@Override
	public ContactGroup saveContactGroup(CreateContactGroup createContactGroup) {



		var contactGroupEntity=new ContactGroupEntity();
		contactGroupEntity.setGroupName(createContactGroup.getGroupName());
		contactGroupEntity.setContacts(createContactGroup.getContactNumbers().stream()
				.map(item -> ContactEntity.toEntity(item,contactGroupEntity))
				.collect(Collectors.toSet()));

		return contactGroupJpaRepository.save(contactGroupEntity).toModel();

	}

	@Override
	public List<ContactGroup> findAll() {
		return contactGroupJpaRepository.findAll()
				.stream()
				.map(ContactGroupEntity::toModel)
				.collect(Collectors.toList());
	}

	@Override
	public ContactGroup findById(Long id) {
		return contactGroupJpaRepository.findById(id).
				orElseThrow(()->new CampaignManagementException("contact.group.not.found"))
				.toModel();
	}

	@Override
	public ContactGroup deleteById(Long id) {
		var ruleGroup=campaignRuleGroupJpaRepository.findByContactGroupId(id);
		if(ruleGroup.isEmpty()){
			contactGroupJpaRepository.deleteById(id);
			return ContactGroup.builder().contactGroupId(id).build();
		}else{
			throw new CampaignManagementException("contact.group.can.not.delete");
		}

	}

	@Override
	public List<Contact> findContactsById(Long contactGroupId) {
		return contactJpaRepository
				.findByContactGroupId(contactGroupId)
				.stream()
				.map(ContactEntity::toModel)
				.collect(Collectors.toList());
	}

	@Override
	public Boolean isContactInContactGroup(Long contactGroupId, String contactNo) {
		var contact=contactJpaRepository.findByContactGroupIdAndContactNo(contactGroupId,contactNo);
		return contact.isPresent();

	}


	@Override
	public PageContent<ContactGroup> findGiftCodesByCriteria(ContactGroupCriteria contactGroupCriteria, Pageable pageable) {
		var contactGroups=contactGroupJpaRepository
				.findAll(matchedCriteria(contactGroupCriteria,pageable),PageRequest.of(pageable.getPageNumber()-1,pageable.getPageSize()));

		return PageContent.<ContactGroup>builder()
				.content(contactGroups.getContent()
						.stream()
						.map(item ->item.toModel()).toList())
				.size(pageable.getPageSize())
				.page(pageable.getPageNumber())
				.totalItems(contactGroups.getTotalElements())
				.build();
	}

	private Specification<ContactGroupEntity> matchedCriteria(ContactGroupCriteria contactGroupCriteria, Pageable pageable) {


		return (root, query, builder) -> {

			List<Predicate> predicates = predicating(contactGroupCriteria, root, builder);

			sorting(pageable, root, query, builder);

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

	private static List<Predicate> predicating(ContactGroupCriteria contactGroupCriteria, Root<ContactGroupEntity> root, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<>();
		if(contactGroupCriteria.getGroupName()!=null && !contactGroupCriteria.getGroupName().isEmpty()){
			final Path<String> name=getPath(String.class, root, "contactGroup.groupName");
			predicates.add(builder.like(builder.lower(name),("%"+ contactGroupCriteria.getGroupName()+"%").toLowerCase()));

		}

		return predicates;
	}

	private static void sorting(Pageable pageable, Root<ContactGroupEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
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
