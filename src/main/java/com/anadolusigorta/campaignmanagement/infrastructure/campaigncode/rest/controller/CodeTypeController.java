package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.facade.CodeTypeFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.rest.dto.response.CodeTypeResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@ApiManagementController
public class CodeTypeController extends BaseController {

    private final CodeTypeFacade codeTypeFacade;

    @GetMapping(value = "code-type")
    public Response<?> findAllCodeTypes() {
        return respond(CodeTypeResponse.fromListOfModel(codeTypeFacade.findAllCodeTypes()));
    }

}
