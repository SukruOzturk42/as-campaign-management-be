package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeType;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.port.CodeTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.CodeTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository.CodeTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CodeTypeRepositoryJpaAdapter implements CodeTypeRepository {

    private final CodeTypeJpaRepository codeTypeJpaRepository;

    @Override
    public List<CodeType> findAllCodeTypes() {
        return codeTypeJpaRepository.findAll().stream().map(CodeTypeEntity::toModel).collect(Collectors.toList());
    }
}
