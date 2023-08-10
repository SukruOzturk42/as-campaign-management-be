package com.anadolusigorta.campaignmanagement.domain.attribute.facade;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeType;
import com.anadolusigorta.campaignmanagement.domain.attribute.port.AttributeTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributeTypeFacade {

    private final AttributeTypeRepository attributeTypeRepository;

    public List<AttributeType> getAttributeTypes() { return attributeTypeRepository.findAllAttributeTypes(); }

}
