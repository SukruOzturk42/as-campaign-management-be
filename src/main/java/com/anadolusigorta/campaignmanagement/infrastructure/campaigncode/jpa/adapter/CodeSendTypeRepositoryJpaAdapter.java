package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeSendType;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.port.CodeSendTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.CodeSendTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository.CodeSendTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CodeSendTypeRepositoryJpaAdapter implements CodeSendTypeRepository {

    private final CodeSendTypeJpaRepository codeSendTypeJpaRepository;

    @Override
    public List<CodeSendType> findAllCodeSendTypes() {
        return codeSendTypeJpaRepository.findAll().stream().map(CodeSendTypeEntity::toModel).collect(Collectors.toList());
    }
}
