package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.CreateTaskGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskGroupRequest {


    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String text;

    private String description;

    private List<String> policyNumbers=new ArrayList<>();

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @NotNull
    private Long taskTypeId;

    private Long goal;

    public CreateTaskGroup toModel(){

        if(endDate.isBefore(startDate)){
            throw new CampaignManagementException("end.date.cannot.earlier.start.date");
        }

        return CreateTaskGroup.builder()
                .id(id)
                .name(name)
                .text(text)
                .description(description)
                .policyNumbers(policyNumbers)
                .taskTypeId(taskTypeId)
                .startDate(startDate)
                .endDate(endDate)
                .goal(goal)
                .build();

    }

}
