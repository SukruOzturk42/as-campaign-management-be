/* odeon_sukruo created on 26.04.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.campaign.model */

package com.anadolusigorta.campaignmanagement.domain.campaign.model;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.operator.AttributeOperator;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AttributeReferenceType;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.DataType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignAttribute {

	private Long id;

	private Long parentId;

	private String name;

	private String description;

	private List<AttributeOperator> operators;

	private List<AttributeReferenceType> referenceDataList;

	private DataType dataType;

	private Integer rank;

	private Boolean hasChild;

	private AttributeTypeEnum attributeType;

	private long order;

	private String campaignType;

	private String campaignStructure;

}
