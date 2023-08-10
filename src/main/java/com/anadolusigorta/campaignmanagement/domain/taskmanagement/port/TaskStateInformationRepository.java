package com.anadolusigorta.campaignmanagement.domain.taskmanagement.port;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskStateInformation;

public interface TaskStateInformationRepository {

    TaskStateInformation saveTaskState(Long taskId, Long taskStateId, Long taskSubStateId, String note);
}
