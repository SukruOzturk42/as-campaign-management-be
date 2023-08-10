package com.anadolusigorta.campaignmanagement.domain.campaigncode.port;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeType;

import java.util.List;

public interface CodeTypeRepository {

    List<CodeType> findAllCodeTypes();

}
