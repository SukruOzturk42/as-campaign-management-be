package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.facade.CodeSendTypeFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.rest.dto.response.CodeSendTypeResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@ApiManagementController
public class CodeSendTypeController extends BaseController {

    private final CodeSendTypeFacade codeSendTypeFacade;

    @GetMapping(value = "code-send-type")
    public Response<?> findAllCodeSendTypes() {
        return respond(CodeSendTypeResponse.fromListOfModel(codeSendTypeFacade.findAllCodeSendTypes()));
    }

}
