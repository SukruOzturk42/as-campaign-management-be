package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.facade.AgencyTaskFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.response.AgencyTaskResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.response.CustomerTasksResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@ApiController
@RequiredArgsConstructor
public class AgencyTaskController extends BaseController {

    private final AgencyTaskFacade agencyTaskFacade;

    @GetMapping("agency-tasks")
    public Response<?> getAgencyTasksByAgencyCode(@RequestParam(value = "agencyCode") String agencyCode) {
        return respond(AgencyTaskResponse.fromModel(agencyTaskFacade.getAgencyTasksByAgencyCode(agencyCode),agencyCode));
    }

    @GetMapping("customer-tasks")
    public Response<?> getAgencyTasksByCustomerNo(@RequestParam(value = "customerNo") String customerNo) {
        return respond(CustomerTasksResponse.fromModel(agencyTaskFacade.getAgencyTasksByCustomerNo(customerNo),customerNo));
    }

}
