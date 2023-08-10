/* dks20165 created on 13.09.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.contact.port */

package com.anadolusigorta.campaignmanagement.domain.contact.port;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeCriteria;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCode;
import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;
import com.anadolusigorta.campaignmanagement.domain.contact.model.Contact;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactGroup;
import com.anadolusigorta.campaignmanagement.domain.contact.model.ContactGroupCriteria;
import com.anadolusigorta.campaignmanagement.domain.contact.model.CreateContactGroup;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContactGroupRepository {

	 ContactGroup saveContactGroup(CreateContactGroup createContactGroup);

	  List<ContactGroup> findAll();
	  PageContent<ContactGroup> findGiftCodesByCriteria(ContactGroupCriteria contactGroupCriteria, Pageable pageable);

	   ContactGroup findById(Long id);


	  ContactGroup deleteById(Long id);

	  List<Contact> findContactsById(Long contactGroupId);

	  Boolean isContactInContactGroup(Long contactGroupId,String contactNo);
}
