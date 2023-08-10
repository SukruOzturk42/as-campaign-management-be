package com.anadolusigorta.campaignmanagement.domain.referencetype.port;


import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AttributeReferenceType;

import java.util.List;

public interface ReferenceTypeRepository {
    List<AttributeReferenceType> findByCampaignAttributeId(Long campaignAttributeId);

    List<AttributeReferenceType> findByAttributeId(Long attributeId);

    List<AttributeReferenceType> findByAttributeNameAndCampaignType(String attributeName,Long campaignTypeId);

    AttributeReferenceType createOrUpdateAttributeReferenceType(AttributeReferenceType attributeReferenceType);

    void deleteReferenceType(Long id);

    List<AttributeReferenceType> findByAttributeNameAndCampaignTypeName(String attributeName, String campaignTypeName);
}
