package com.anadolusigorta.campaignmanagement.domain.taskmanagement.facade;


import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskProcessHistory;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.port.TaskProcessHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskProcessHistoryFacade {

    private final TaskProcessHistoryRepository taskProcessHistoryRepository;

    public TaskProcessHistory saveHistory(TaskProcessHistory taskProcessHistory){
        return taskProcessHistoryRepository.saveHistory(taskProcessHistory);
    }

    public List<TaskProcessHistory> getTaskProcessHistories() {
        return taskProcessHistoryRepository.getTaskProcessHistories();
    }

    public List<TaskProcessHistory> getTaskProcessHistoriesByTaskId(Long taskId) {
        return taskProcessHistoryRepository.getTaskProcessHistoriesByTaskId(taskId);
    }
}
