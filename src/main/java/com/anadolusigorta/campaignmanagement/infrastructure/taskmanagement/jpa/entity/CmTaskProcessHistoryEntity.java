package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskProcessHistory;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cm_task_process_history")
public class CmTaskProcessHistoryEntity extends AbstractEntity {

    @Column(name = "taskId")
    private Long taskId;

    @Column(name = "username")
    private String username;

    @ManyToOne
    @JoinColumn(name = "task_state_type_id")
    private TaskStateTypeEntity taskStateType;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String desc;

    @Column(name = "process_date")
    private LocalDateTime processDate;

    public TaskProcessHistory toModel() {
        return TaskProcessHistory.builder()
                .id(getId())
                .taskState(taskStateType != null ? taskStateType.toModel() : null)
                .taskId(taskId)
                .processDate(processDate)
                .desc(desc)
                .name(name)
                .username(username)
                .build();
    }
}
