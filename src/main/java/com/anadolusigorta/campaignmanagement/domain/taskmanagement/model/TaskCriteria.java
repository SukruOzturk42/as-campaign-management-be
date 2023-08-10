package com.anadolusigorta.campaignmanagement.domain.taskmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskCriteria {

    List<String> agencyCodes;

    List<Long> regionCodes;

    List<Long> taskTypes;

    List<Long> taskStateTypes;

    List<Long> taskSubStateTypes;

    LocalDateTime periodStartDate;

    Long taskId;

    String contactNumber;

    LocalDateTime expireDate;
}
