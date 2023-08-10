package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.taskmanagement.model.TaskType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "knime_task_type")
public class KnimeTaskTypeEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name")
    private String name;

    public TaskType toModel(){
        return TaskType.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static KnimeTaskTypeEntity fromModel(TaskType taskType) {
        return KnimeTaskTypeEntity.builder()
                .id(taskType.getId())
                .name(taskType.getName())
                .build();
    }


}
