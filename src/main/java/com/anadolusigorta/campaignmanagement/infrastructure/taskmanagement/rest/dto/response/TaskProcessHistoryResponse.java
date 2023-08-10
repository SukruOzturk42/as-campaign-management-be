package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskProcessHistory;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskStateType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskProcessHistoryResponse {

    Long id;

    Long taskId;

    String username;

    String taskStateType;

    String name;

    String desc;

    LocalDateTime processDate;

    public static TaskProcessHistoryResponse fromModel(TaskProcessHistory taskProcessHistory){
        return TaskProcessHistoryResponse.builder()
                .id(taskProcessHistory.getId())
                .taskStateType(taskProcessHistory.getTaskState() != null ? taskProcessHistory.getTaskState().getDescription() : null)
                .taskId(taskProcessHistory.getTaskId())
                .desc(taskProcessHistory.getDesc())
                .processDate(taskProcessHistory.getProcessDate())
                .name(taskProcessHistory.getName())
                .username(taskProcessHistory.getUsername())
                .build();
    }

    public static List<TaskProcessHistoryResponse> fromListOfModel(List<TaskProcessHistory> taskProcessHistories){
        return taskProcessHistories.stream().map(TaskProcessHistoryResponse::fromModel).collect(Collectors.toList());
    }

}
