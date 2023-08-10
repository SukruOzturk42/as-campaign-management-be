package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.facade.TaskStateTypeFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiTaskManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@ApiTaskManagementController
@RequiredArgsConstructor
public class TaskStateTypeController extends BaseController {

    private final TaskStateTypeFacade taskStateTypeFacade;

    @GetMapping("task-filtered-state-types")
    public Response<?> getFilteredStateTypes() {
        return respond(taskStateTypeFacade.getFilteredStateTypes());
    }

    @GetMapping("task-state-types")
    public Response<?> getStateTypes() {
        return respond(taskStateTypeFacade.getStateTypes());
    }

    @GetMapping("task-sub-state-types/{taskStateTypeId}")
    public Response<?> getSubStateTypes(@PathVariable("taskStateTypeId") Long taskStateTypeId ) {
        return respond(taskStateTypeFacade.getSubStateTypes(taskStateTypeId));
    }

    @GetMapping("task-sub-state-types")
    public Response<?> getSubStateTypesByStateTypes(@RequestParam(value = "taskStateTypes") List<Long> taskStateTypes ) {
        return respond(taskStateTypeFacade.getSubStateTypesByStateTypes(taskStateTypes));
    }
}
