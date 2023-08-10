package com.anadolusigorta.campaignmanagement.domain.referencetype.facade;

import com.anadolusigorta.campaignmanagement.domain.referencetype.model.AttributeReferenceType;
import com.anadolusigorta.campaignmanagement.domain.referencetype.port.ReferenceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReferenceTypeFacade {
    private final ReferenceTypeRepository referenceTypeRepository;

    public List<AttributeReferenceType> getReferenceTypeByCampaignAttributeId(Long campaignAttributeId) {
        return referenceTypeRepository.findByCampaignAttributeId(campaignAttributeId);
    }

    public List<AttributeReferenceType> getReferenceTypeByAttributeId(Long attributeId) {
        return referenceTypeRepository.findByAttributeId(attributeId);
    }

    public List<AttributeReferenceType> getReferenceTypeByAttributeNameAndCampaignType(String attributeName,Long campaignTypeId) {
        return referenceTypeRepository.findByAttributeNameAndCampaignType(attributeName,campaignTypeId);
    }

    public AttributeReferenceType createOrUpdateAttributeReferenceType(AttributeReferenceType attributeReferenceType) {
        return referenceTypeRepository.createOrUpdateAttributeReferenceType(attributeReferenceType);
    }

    public void deleteReferenceType(Long id){
        referenceTypeRepository.deleteReferenceType(id);
    }

    public List<AttributeReferenceType> getReferenceTypeByAttributeNameAndCampaignTypeName(String attributeName, String campaignTypeName) {
        return referenceTypeRepository.findByAttributeNameAndCampaignTypeName(attributeName,campaignTypeName);
    }
}
