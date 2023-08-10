package com.anadolusigorta.campaignmanagement.domain.taskmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskSuccessRate {

    String taskListName;

    String taskTypeName;

    Long totalTasks;

    Long successTasks;

    String ratio;

    Long goal;
}
