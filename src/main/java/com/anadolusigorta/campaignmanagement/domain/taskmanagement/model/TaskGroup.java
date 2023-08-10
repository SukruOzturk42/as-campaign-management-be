package com.anadolusigorta.campaignmanagement.domain.taskmanagement.model;

import com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity.KnimeTaskTypeEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskGroup {


    private Long id;

    private String name;

    private String text;

    private String description;

    private List<String> policyNumbers=new ArrayList<>();

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime createdAt;

    private TaskType taskType;

    private Long goal;


    public boolean hasPolicyType(String policyType){
        var policies=policyNumbers.stream().map(String::trim).toList();
        return policies.contains(policyType);
    }

}
