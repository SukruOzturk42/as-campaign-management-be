package com.anadolusigorta.campaignmanagement.domain.attribute.port;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.Attribute;
import com.anadolusigorta.campaignmanagement.domain.operator.AttributeOperator;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AttributeReferenceType;

import java.util.List;

public interface AttributeRepository {

    Attribute findById(Long id);

    List<Attribute> findAllAttributes();

    List<AttributeOperator> findAttributeOperatorByCampaignAttributeId(Long campaignAttributeId);

    List<Attribute> findAttributeByCampaignTypeId(Long campaignTypeId,String structureName);

    List<AttributeOperator> findAttributeOperatorByAttributeId(Long attributeId);

    AttributeOperator addAttributeOperator(Long attributeId, String operator);

    AttributeOperator updateAttributeOperator(Long attributeId, String operator,Long id);

    AttributeOperator deleteAttributeOperator(Long id);

    Attribute saveAttribute(Attribute attribute,Long campaignTypeId);

    Attribute deleteAttribute(Long id);

    List<AttributeReferenceType> getAttributeReferenceTypeByName(String name);

    List<Attribute> findAllAttributeByCampaignTypeId(Long campaignTypeId);

}
