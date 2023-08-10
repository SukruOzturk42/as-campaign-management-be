package com.anadolusigorta.campaignmanagement.domain.campaigncode.port;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeSendType;

import java.util.List;

public interface CodeSendTypeRepository {

    List<CodeSendType> findAllCodeSendTypes();

}
