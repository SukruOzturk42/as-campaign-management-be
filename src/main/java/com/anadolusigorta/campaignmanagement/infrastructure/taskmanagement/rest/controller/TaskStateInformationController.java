package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.facade.TaskStateInformationFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiTaskManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@ApiTaskManagementController
@RequiredArgsConstructor
@Validated
public class TaskStateInformationController extends BaseController {

    private final TaskStateInformationFacade taskStateInformationFacade;

    @PostMapping("task-state-information")
    public Response<?> saveTaskState(@RequestParam(value = "taskId") Long taskId,
                                     @RequestParam(value = "taskStateId") Long taskStateId,
                                     @RequestParam(value = "taskSubStateId") Long taskSubStateId,
                                     @Valid @RequestParam(value = "note")
                                         @Size(max = 255, message = "Not alanı için girilen değer 255 karakterden fazla olamaz.") String note) {
        return respond(taskStateInformationFacade.saveTaskState(taskId,taskStateId,taskSubStateId,note));
    }

}
