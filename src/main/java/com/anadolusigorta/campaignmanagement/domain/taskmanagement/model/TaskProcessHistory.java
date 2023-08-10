package com.anadolusigorta.campaignmanagement.domain.taskmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskProcessHistory {

    private Long id;

    private Long taskId;

    private String username;

    private TaskStateType taskState;

    private String name;

    private String desc;

    private LocalDateTime processDate;

}
