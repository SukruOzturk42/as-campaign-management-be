package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity;


import com.anadolusigorta.campaignmanagement.domain.saletask.model.TaskState;
import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskStateInformation;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task_state_information")
public class TaskStateInformationEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "task_state_type_id")
    private TaskStateTypeEntity taskStateType;

    @ManyToOne
    @JoinColumn(name = "task_sub_state_type_id")
    private TaskSubStateTypeEntity taskSubStateType;

    @Column(name = "note")
    private String note;

    public TaskStateInformation toModel(){
        return TaskStateInformation.builder()
                .taskState(taskStateType != null ? taskStateType.toModel() : null)
                .taskSubState(taskSubStateType != null ? taskSubStateType.toModel() : null)
                .note(note)
                .build();
    }

}
