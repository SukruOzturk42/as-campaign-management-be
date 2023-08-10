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
public class SuccessRateCriteria {

    List<String> taskCriteria;

    String role;

    LocalDateTime first;

    LocalDateTime last;

}
