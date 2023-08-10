package com.anadolusigorta.campaignmanagement.infrastructure.saletask.jpa.entity;


import com.anadolusigorta.campaignmanagement.domain.saletask.model.SaleTask;
import com.anadolusigorta.campaignmanagement.domain.saletask.model.TaskState;
import com.anadolusigorta.campaignmanagement.domain.saletask.model.TaskStatus;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale_task")
public class SaleTaskEntity extends AbstractEntity {


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "task_owner")
    private String taskOwner;

    @Column(name = "description")
    private String description;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private TaskState state;

    @ManyToOne
    @JoinColumn(name = "sale_task_group_id", nullable = false)
    private SaleTaskGroupEntity saleTaskGroup;

    @ManyToOne
    @JoinColumn(name = "agency_id", nullable = false)
    private AgencyEntity agency;

    public SaleTask toModel(){
        return SaleTask.builder()
                .status(status)
                .taskState(state)
                .description(description)
                .taskOwner(taskOwner)
                .agency(agency.toModel())
                .saleTaskGroup(saleTaskGroup.toModel())
                .build();
    }

}
