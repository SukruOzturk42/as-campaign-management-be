package com.anadolusigorta.campaignmanagement.domain.campaigncode.facade;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeSendType;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.port.CodeSendTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeSendTypeFacade {

    private final CodeSendTypeRepository codeSendTypeRepository;

    public List<CodeSendType> findAllCodeSendTypes() {
        return codeSendTypeRepository.findAllCodeSendTypes();
    }

}
