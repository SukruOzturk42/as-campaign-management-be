package com.anadolusigorta.campaignmanagement.domain.campaigncode.port;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeKind;

import java.util.List;

public interface CodeKindRepository {

    List<CodeKind> findAllCodeKinds();

}
