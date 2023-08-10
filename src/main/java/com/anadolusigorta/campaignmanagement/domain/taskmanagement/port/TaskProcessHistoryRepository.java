package com.anadolusigorta.campaignmanagement.domain.taskmanagement.port;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskProcessHistory;

import java.util.List;

public interface TaskProcessHistoryRepository {
    TaskProcessHistory saveHistory(TaskProcessHistory taskProcessHistory);

    List<TaskProcessHistory> getTaskProcessHistories();

    List<TaskProcessHistory> getTaskProcessHistoriesByTaskId(Long taskId);
}
