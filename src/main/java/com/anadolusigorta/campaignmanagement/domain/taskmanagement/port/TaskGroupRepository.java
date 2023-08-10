package com.anadolusigorta.campaignmanagement.domain.taskmanagement.port;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.CreateTaskGroup;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskGroup;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface TaskGroupRepository {

    TaskGroup save(CreateTaskGroup taskGroup);

    TaskGroup delete(Long taskGroupId);

    List<TaskGroup> getAllTaskGroups();

    List<TaskGroup> getAllTaskGroupsByDates(LocalDateTime start, LocalDateTime end);

    TaskGroup update(CreateTaskGroup taskGroup);

    TaskGroup getTaskGroupById(Long id);

    Set<TaskType> getTaskGroupTaskTypes();
}
