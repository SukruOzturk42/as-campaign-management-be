package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity;

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
@Table(name = "task_sub_state_type")
public class TaskSubStateTypeEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "task_state_type_id")
    private TaskStateTypeEntity taskStateType;

    @Column(name = "access_role_codes")
    private String accessRoleCodes;

    public TaskStateType toModel(){
        return TaskStateType.builder()
                .id(getId())
                .description(description)
                .name(name)
                .build();
    }

}
