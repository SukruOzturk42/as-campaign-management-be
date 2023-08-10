package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.controller;


import com.anadolusigorta.campaignmanagement.domain.taskmanagement.facade.CmTaskFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiTaskManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.PageableResponse;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.request.TaskAgencyRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.request.TaskSuccessRateRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.response.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ApiTaskManagementController
@RequiredArgsConstructor
public class TaskController extends BaseController {

    private final CmTaskFacade taskFacade;


    @PostMapping("pageable-agency-tasks")
    public PageableResponse<?> getPageableAllAgencyTasks(@RequestBody @Valid TaskAgencyRequest taskAgencyRequest,
                                                         @NotNull final Pageable pageable) {
        return respond(TaskResponse
                .fromListOfModel(taskFacade
                        .getByCriteria(taskAgencyRequest.toModel(), pageable)));
    }

    @GetMapping("role-task-agencies")
    public Response<?> getRoleTaskAgencies() {
        return respond(taskFacade.getRoleTaskAgency());
    }

    @GetMapping("agency-users-agencies")
    public Response<?> getAgencyUserAgencies() {
        return respond(taskFacade.getAgencyUserAgencies());
    }

    @GetMapping("region-codes")
    public Response<?> getRegionCodes() {
        return respond(taskFacade.getRegionCodes());
    }

    @PostMapping("task-success-rates")
    public Response<?> getSuccessRateByRole(@RequestBody TaskSuccessRateRequest taskSuccessRateRequest) {
        return respond(taskFacade.getSuccessRateByRole(taskSuccessRateRequest.toModel()));
    }

    @GetMapping("task-types")
    public Response<?> getAllTaskType() {
        return respond(taskFacade.getAllTaskType());
    }

    @GetMapping("task-group-task-types")
    public Response<?> getAllTaskTypeByTaskGroup() {
        return respond(taskFacade.getAllTaskTypeByTaskGroup());
    }

    @GetMapping("users-by-agency-code")
    public Response<?> getUsersByAgencyCode(@RequestParam(value = "agencyCode") String agencyCode) {
        return respond(taskFacade.getTaskAgencyUsers(agencyCode));
    }

    @PostMapping("task")
    public Response<?> saveTaskOwner(@RequestParam(value = "taskId") Long taskId,
                                     @RequestParam(value = "taskOwnerName") String taskOwnerName,
                                     @RequestParam(value = "taskOwnerUserName") String taskOwnerUserName) {
        return respond(TaskResponse.fromModel(taskFacade.saveTaskOwner(taskId, taskOwnerName, taskOwnerUserName)));
    }


}


