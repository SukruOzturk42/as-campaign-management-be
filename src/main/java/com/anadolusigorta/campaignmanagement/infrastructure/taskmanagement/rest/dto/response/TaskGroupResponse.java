package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskGroupResponse {

    private Long id;

    private String name;

    private String text;

    private String description;

    private List<String> policyNumbers=new ArrayList<>();

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime createdAt;

    private Long taskTypeId;

    private String taskTypeName;

    private Long goal;


    public static TaskGroupResponse fromModel(TaskGroup taskGroup) {
        return TaskGroupResponse.builder()
                .id(taskGroup.getId())
                .name(taskGroup.getName())
                .text(taskGroup.getText())
                .description(taskGroup.getDescription())
                .policyNumbers(taskGroup.getPolicyNumbers())
                .taskTypeId(taskGroup.getTaskType().getId())
                .startDate(taskGroup.getStartDate())
                .endDate(taskGroup.getEndDate())
                .createdAt(taskGroup.getCreatedAt())
                .taskTypeName(taskGroup.getTaskType().getName())
                .goal(taskGroup.getGoal())
                .build();
    }

    public static List<TaskGroupResponse> fromListOfModel(List<TaskGroup> taskGroup) {
        return taskGroup.stream().map(TaskGroupResponse::fromModel).collect(Collectors.toList());
    }
}
