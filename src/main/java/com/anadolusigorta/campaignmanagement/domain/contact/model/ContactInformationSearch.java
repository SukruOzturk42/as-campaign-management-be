/* odeon_sukruo created on 21.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.product */

package com.anadolusigorta.campaignmanagement.domain.contact.model;

import com.anadolusigorta.campaignmanagement.domain.ownerproduct.model.OwnerProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactInformationSearch {

	private String contactNumber;
	private String identityNumber;
	private String identityType;
	private String salesChannel;


}
