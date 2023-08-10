package com.anadolusigorta.campaignmanagement.domain.campaigncode.facade;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeKind;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.port.CodeKindRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeKindFacade {

    private final CodeKindRepository codeKindRepository;

    public List<CodeKind> findAllCodeKinds() {
        return codeKindRepository.findAllCodeKinds();
    }


}
