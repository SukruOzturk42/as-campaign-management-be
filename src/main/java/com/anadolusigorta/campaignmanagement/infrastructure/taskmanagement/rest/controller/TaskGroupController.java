package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.controller;


import com.anadolusigorta.campaignmanagement.domain.taskmanagement.facade.TaskGroupFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiTaskManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.request.CreateTaskGroupRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.response.TaskGroupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@ApiTaskManagementController
@RequiredArgsConstructor
public class TaskGroupController  extends BaseController {

    private final TaskGroupFacade taskGroupFacade;

    @PostMapping("task-group")
    public Response<?> saveTaskGroup(@RequestBody @Valid CreateTaskGroupRequest request) {
        return respond(TaskGroupResponse.fromModel(taskGroupFacade.save(request.toModel())));
    }

    @GetMapping("task-groups")
    public Response<?> getAllTasks(){
        return respond(TaskGroupResponse.fromListOfModel(taskGroupFacade.getAllTasks()));
    }

    @GetMapping("task-group/{id}")
    public Response<?> getTaskGroupById(@PathVariable("id") Long id){
        return respond(TaskGroupResponse.fromModel(taskGroupFacade.getTaskGroupById(id)));
    }

    @DeleteMapping("task-group")
    public Response<?> deleteTaskGroup(@RequestParam( value = "id" ) Long id){
        return respond(TaskGroupResponse.fromModel(taskGroupFacade.delete(id)));
    }

    @PutMapping("task-group")
    public Response<?> updateTaskGroup(@RequestBody @Valid CreateTaskGroupRequest request){
        return respond(TaskGroupResponse.fromModel(taskGroupFacade.update(request.toModel())));
    }

}


