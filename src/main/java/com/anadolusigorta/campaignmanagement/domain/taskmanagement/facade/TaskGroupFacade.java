package com.anadolusigorta.campaignmanagement.domain.taskmanagement.facade;


import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.CreateTaskGroup;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskGroup;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.port.TaskGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskGroupFacade {

    private final TaskGroupRepository taskGroupRepository;

    public TaskGroup save(CreateTaskGroup taskGroup){
        return taskGroupRepository.save(taskGroup);
    }

   public TaskGroup delete(Long taskGroupId){
        return taskGroupRepository.delete(taskGroupId);
    }

    public List<TaskGroup> getAllTasks() {return taskGroupRepository.getAllTaskGroups();}

    public TaskGroup update(CreateTaskGroup taskGroup) {
        return taskGroupRepository.update(taskGroup);
    }

    public TaskGroup getTaskGroupById(Long id) {
        return taskGroupRepository.getTaskGroupById(id);
    }
}
