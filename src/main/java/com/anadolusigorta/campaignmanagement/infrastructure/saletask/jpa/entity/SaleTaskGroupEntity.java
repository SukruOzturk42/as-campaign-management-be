package com.anadolusigorta.campaignmanagement.infrastructure.saletask.jpa.entity;


import com.anadolusigorta.campaignmanagement.domain.saletask.model.SaleTaskGroup;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale_task_group")
public class SaleTaskGroupEntity extends AbstractEntity {


    @OneToMany(mappedBy = "saleTaskGroup",cascade = CascadeType.ALL)
    private Set<SaleTaskEntity> saleTasks;

    @Column(name = "type")
    private String type;

    public SaleTaskGroup toModel(){
        return SaleTaskGroup.builder()
                .type(type)
                .build();
    }


}
