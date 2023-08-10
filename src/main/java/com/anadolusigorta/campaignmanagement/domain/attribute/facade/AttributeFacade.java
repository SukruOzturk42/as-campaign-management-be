package com.anadolusigorta.campaignmanagement.domain.attribute.facade;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.Attribute;
import com.anadolusigorta.campaignmanagement.domain.attribute.port.AttributeRepository;
import com.anadolusigorta.campaignmanagement.domain.operator.AttributeOperator;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AttributeReferenceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributeFacade {
    private final AttributeRepository attributeRepository;

    public Attribute getAttributeById(Long id){
        return attributeRepository.findById(id);
    }

    public List<Attribute> getAttributes() {
        return attributeRepository.findAllAttributes();
    }

    public List<AttributeOperator> getAttributeOperator(Long campaignAttributeId){
        return attributeRepository.findAttributeOperatorByCampaignAttributeId(campaignAttributeId);
    }

    public List<Attribute> getAttributeByCampaignTypeIdAndStructureName(Long campaignTypeId,String structureName) {
        return attributeRepository.findAttributeByCampaignTypeId(campaignTypeId,structureName);
    }

    public List<Attribute> getAllAttributeByCampaignTypeId(Long campaignTypeId) {
        return attributeRepository.findAllAttributeByCampaignTypeId(campaignTypeId);
    }

    public List<AttributeOperator> getAttributeOperatorByAttributeId(Long attributeId) {
        return attributeRepository.findAttributeOperatorByAttributeId(attributeId);
    }

    public AttributeOperator addAttributeOperator(Long attributeId,String operator){
        return attributeRepository.addAttributeOperator(attributeId,operator);
    }

    public AttributeOperator updateAttributeOperator(Long attributeId,String operator,Long id){
        return attributeRepository.updateAttributeOperator(attributeId,operator,id);
    }

    public AttributeOperator deleteOperatorByAttributeId(Long id){
        return attributeRepository.deleteAttributeOperator(id);
    }

    public Attribute saveAttribute(Attribute attribute, Long campaignTypeId){
        return attributeRepository.saveAttribute(attribute,campaignTypeId);
    }

    public Attribute deleteAttribute(Long id){
        return attributeRepository.deleteAttribute(id);
    }

    public List<AttributeReferenceType> getAttributeReferenceTypeByName(String name) {
        return attributeRepository.getAttributeReferenceTypeByName(name);
    }

}
