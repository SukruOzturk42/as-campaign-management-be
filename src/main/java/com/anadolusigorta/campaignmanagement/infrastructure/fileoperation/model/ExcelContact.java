/* dks20165 created on 25.08.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.fileoperation.model */

package com.anadolusigorta.campaignmanagement.infrastructure.fileoperation.model;

import com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.entity.ContactEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.contact.jpa.entity.ContactGroupEntity;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@Setter
public class ExcelContact {

	public static final String EXCEL_SHEET_NAME="contacts";
	public static final String RULE_GROUP_CUSTOMER_EXCEL_CUSTOMER_NO_HEADER_NAME="MUSTERI_NO";

	@SerializedName(RULE_GROUP_CUSTOMER_EXCEL_CUSTOMER_NO_HEADER_NAME)
	Long contactNo;

	public Long getContactNo(){
		return contactNo;
	}

     public ContactEntity toEntity(){
		var entity=new ContactEntity();
		entity.setContactNo(contactNo.toString());
		return entity;
	 }

	public ContactEntity toEntity(ContactGroupEntity contactGroupEntity){
		var entity=new ContactEntity();
		entity.setContactGroup(contactGroupEntity);
		entity.setContactNo(contactNo.toString());
		return entity;
	}
}
