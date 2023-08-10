package com.anadolusigorta.campaignmanagement.domain.taskmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskStateInformation {

    private Long id;

    private String note;

    private TaskStateType taskState;

    private TaskStateType taskSubState;

}
