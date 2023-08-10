package com.anadolusigorta.campaignmanagement.infrastructure.operator.rest;

import com.anadolusigorta.campaignmanagement.domain.operator.OperatorEnum;
import com.anadolusigorta.campaignmanagement.domain.referencetype.model.DataType;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.stream.Collectors;

@ApiManagementController
@RequiredArgsConstructor
public class OperatorController extends BaseController {


    @GetMapping("operators")
    public Response<?> getOperators() {
        return respond(Arrays.stream(OperatorEnum.values())
                .map(Enum::toString)
                .collect(Collectors.toList()));
    }

    @GetMapping("data-types")
    public Response<?> getDataTypes() {
        return respond(Arrays.stream(DataType.values())
                .map(Enum::toString)
                .collect(Collectors.toList()));
    }

}
