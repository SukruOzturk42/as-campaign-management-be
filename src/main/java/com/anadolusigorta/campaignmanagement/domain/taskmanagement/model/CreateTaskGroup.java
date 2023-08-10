package com.anadolusigorta.campaignmanagement.domain.taskmanagement.model;

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
public class CreateTaskGroup {


    private Long id;

    private String name;

    private String text;

    private String description;

    private List<String> policyNumbers=new ArrayList<>();

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long taskTypeId;

    private Long goal;

}
