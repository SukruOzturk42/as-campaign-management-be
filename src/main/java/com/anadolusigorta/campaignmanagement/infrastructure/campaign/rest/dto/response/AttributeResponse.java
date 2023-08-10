/* odeon_sukruo created on 29.04.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignAttribute;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.DataType;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.rest.dto.AttributeOperatorResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.rest.dto.AttributeReferenceTypeResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeResponse {

    private Long id;

    private Long parentId;

    private String name;

    private String description;

    private List<AttributeOperatorResponse> operators;

    private List<AttributeReferenceTypeResponse> referenceDataList;

    private DataType dataType;

    private String campaignType;

    private String campaignStructure;

    private Integer rank;

    private Boolean hasChild;

    private AttributeTypeEnum attributeType;

    private Long order;

    public static AttributeResponse fromModel(CampaignAttribute campaignAttribute) {
        return AttributeResponse.builder()
                .id(campaignAttribute.getId())
                .parentId(campaignAttribute.getParentId())
                .hasChild(campaignAttribute.getHasChild())
                .name(campaignAttribute.getName())
                .description(campaignAttribute.getDescription())
                .dataType(campaignAttribute.getDataType())
                .rank(campaignAttribute.getRank())
                .attributeType(campaignAttribute.getAttributeType())
                .order(campaignAttribute.getOrder())
                .campaignType(campaignAttribute.getCampaignType())
                .campaignStructure(campaignAttribute.getCampaignStructure())
                .operators(!campaignAttribute.getOperators().isEmpty()
                        ? campaignAttribute.getOperators().stream().map(AttributeOperatorResponse::fromModel).collect(Collectors.toList())
                        :new ArrayList<>())
                .build();
    }

    public static List<AttributeResponse> fromListOfModel(List<CampaignAttribute> campaignAttributeList) {
        return campaignAttributeList.stream().map(AttributeResponse::fromModel).collect(Collectors.toList());
    }
}
