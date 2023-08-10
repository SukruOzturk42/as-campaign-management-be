package com.anadolusigorta.campaignmanagement.domain.attribute.port;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeType;

import java.util.List;

public interface AttributeTypeRepository {

    List<AttributeType> findAllAttributeTypes();

}
