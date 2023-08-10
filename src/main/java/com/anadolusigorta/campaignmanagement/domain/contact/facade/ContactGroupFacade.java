/* odeon_sukruo created on 21.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.product.facade */

package com.anadolusigorta.campaignmanagement.domain.contact.facade;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeCriteria;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCode;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.contact.model.Contact;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactGroup;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactGroupCriteria;
import com.anadolusigorta.campaignmanagement.domain.contact.model.CreateContactGroup;
import com.anadolusigorta.campaignmanagement.domain.contact.port.ContactGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactGroupFacade {

	private final ContactGroupRepository contactGroupRepository;


	public ContactGroup saveContactGroup(CreateContactGroup createContactGroup){

		return contactGroupRepository.saveContactGroup(createContactGroup);
	}


	public ContactGroup saveContactGroup(CreateContactGroup createContactGroup,Long id){

		return contactGroupRepository.saveContactGroup(createContactGroup);
	}

	public List<ContactGroup> getAllContactGroup(){

		return contactGroupRepository.findAll();
	}

	public PageContent<ContactGroup> getAllContactGroupsCriteria(ContactGroupCriteria contactGroupCriteria, Pageable pageable) {
		return contactGroupRepository.findGiftCodesByCriteria(contactGroupCriteria, pageable);
	}

	public ContactGroup getContactGroup(Long id){

		return contactGroupRepository.findById(id);
	}

	public ContactGroup deleteContactGroup(Long id){

		return contactGroupRepository.deleteById(id);
	}

	public List<Contact> getContactsByContactGroupId(Long contactGroupId){
		return contactGroupRepository.findContactsById(contactGroupId);
	}
}
