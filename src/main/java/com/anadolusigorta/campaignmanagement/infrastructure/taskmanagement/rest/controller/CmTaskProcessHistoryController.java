package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.facade.TaskProcessHistoryFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiTaskManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.response.TaskProcessHistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@ApiTaskManagementController
@RequiredArgsConstructor
public class CmTaskProcessHistoryController extends BaseController {

    private final TaskProcessHistoryFacade taskProcessHistoryFacade;

    @GetMapping("task-process-histories")
    public Response<?> getTaskProcessHistories() {
        return respond(TaskProcessHistoryResponse.fromListOfModel(taskProcessHistoryFacade.getTaskProcessHistories()));
    }

    @GetMapping("task-process-histories/{taskId}")
    public Response<?> getTaskProcessHistories(@PathVariable(value = "taskId") Long taskId) {
        return respond(TaskProcessHistoryResponse.fromListOfModel(taskProcessHistoryFacade.getTaskProcessHistoriesByTaskId(taskId)));
    }
}
