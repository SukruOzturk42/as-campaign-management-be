package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskCriteria;
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
public class TaskAgencyRequest {

    List<String> agencyCodes;

    List<Long> regionCodes;

    List<Long> taskTypes;

    List<Long> taskStateTypes;

    List<Long> taskSubStateTypes;

    LocalDateTime periodStartDate;

    Long taskId;

    String contactNumber;

    LocalDateTime expireDate;

    public TaskCriteria toModel(){
        return TaskCriteria.builder()
                .agencyCodes(agencyCodes)
                .regionCodes(regionCodes)
                .taskTypes(taskTypes)
                .taskStateTypes(taskStateTypes)
                .taskSubStateTypes(taskSubStateTypes)
                .periodStartDate(periodStartDate)
                .taskId(taskId)
                .contactNumber(contactNumber)
                .expireDate(expireDate)
                .build();
    }
}
