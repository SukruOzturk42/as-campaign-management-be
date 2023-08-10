package com.anadolusigorta.campaignmanagement.domain.taskmanagement.port;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskStateType;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskSubStateType;

import java.util.List;

public interface TaskStateTypeRepository {

    List<TaskStateType> getStateTypes();

    List<TaskStateType> getSubStateTypesByTaskStateTypeId(Long taskStateTypeId);

    List<TaskStateType> getFilteredStateTypes();

    List<TaskStateType> getSubStateTypesByStateTypes(List<Long> taskStateTypes);
}
