package com.anadolusigorta.campaignmanagement.domain.campaigncode.facade;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeType;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.port.CodeTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeTypeFacade {

    private final CodeTypeRepository codeTypeRepository;

    public List<CodeType> findAllCodeTypes() {
        return codeTypeRepository.findAllCodeTypes();
    }

}
