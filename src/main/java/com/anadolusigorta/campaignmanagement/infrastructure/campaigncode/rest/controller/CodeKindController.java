package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.facade.CodeKindFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.rest.dto.response.CodeKindResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@ApiManagementController
public class CodeKindController extends BaseController {

    private final CodeKindFacade codeKindFacade;

    @GetMapping(value = "code-kind")
    public Response<?> findAllCodeKinds() {
        return respond(CodeKindResponse.fromListOfModel(codeKindFacade.findAllCodeKinds()));
    }

}
