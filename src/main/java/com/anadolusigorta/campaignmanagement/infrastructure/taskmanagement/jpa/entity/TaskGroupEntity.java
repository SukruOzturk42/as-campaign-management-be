package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity;


import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.CreateTaskGroup;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskGroup;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task_group")
public class TaskGroupEntity extends AbstractEntity {


    @Column(name = "name")
    private String name;


    @Column(name = "text")
    private String text;


    @Column(name = "description")
    private String description;


    @Column(name = "policy_numbers")
    private String policyNumbers;


    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "task_type_id", nullable = false)
    private KnimeTaskTypeEntity taskType;

    @Column(name = "goal")
    private Long goal;


     public TaskGroup toModel(){
         return TaskGroup.builder()
                 .id(getId())
                 .name(name)
                 .text(text)
                 .description(description)
                 .startDate(startDate)
                 .endDate(endDate)
                 .createdAt(getCreatedAt())
                 .taskType(taskType!=null?taskType.toModel():null)
                 .policyNumbers(policyNumbers!=null?
                         new ArrayList<>(Arrays.asList(policyNumbers.split(Constants.ATTRIBUTE_VALUE_DELIMITER))):
                         null)
                 .goal(goal)
                 .build();
     }
     
}
