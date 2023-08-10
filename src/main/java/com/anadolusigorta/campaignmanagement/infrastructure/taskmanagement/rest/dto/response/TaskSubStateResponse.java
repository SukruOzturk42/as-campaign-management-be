package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskSubStateResponse {

    private Long id;

    private String name;

    private String description;

}
