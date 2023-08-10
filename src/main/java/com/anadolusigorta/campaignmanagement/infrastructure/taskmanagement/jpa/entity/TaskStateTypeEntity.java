package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity;


import com.anadolusigorta.campaignmanagement.domain.saletask.model.TaskState;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskStateType;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task_state_type")
public class TaskStateTypeEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "taskStateType")
    private List<TaskSubStateTypeEntity> taskSubStateTypes;

    public TaskStateType toModel(){
        return TaskStateType.builder()
                .id(getId())
                .description(description)
                .name(name)
                .build();
    }
}
