package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeKind;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.port.CodeKindRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.CodeKindEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository.CodeKindJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CodeKindRepositoryJpaAdapter implements CodeKindRepository {

    private final CodeKindJpaRepository codeKindJpaRepository;

    @Override
    public List<CodeKind> findAllCodeKinds() {
        return codeKindJpaRepository.findAll().stream().map(CodeKindEntity::toModel).collect(Collectors.toList());
    }
}
