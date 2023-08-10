package com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.attribute.model.AttributeType;
import com.anadolusigorta.campaignmanagement.domain.attribute.port.AttributeTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.entity.AttributeTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.attribute.jpa.repository.AttributeTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttributeTypeJpaAdapter implements AttributeTypeRepository {

    private final AttributeTypeJpaRepository attributeTypeJpaRepository;

    @Override
    public List<AttributeType> findAllAttributeTypes() {
        return attributeTypeJpaRepository.findAll().stream()
                .map(AttributeTypeEntity::toModel)
                .collect(Collectors.toList());
    }
}
