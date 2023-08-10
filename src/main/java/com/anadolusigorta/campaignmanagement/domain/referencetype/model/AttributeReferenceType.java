/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.referencedata */

package com.anadolusigorta.campaignmanagement.domain.referencetype.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeReferenceType {

	private Long id;

	private String name;

	private String description;

	private String descriptionEn;

	private Long attributeId;

	private AffinityInformation affinityInformation;


}
