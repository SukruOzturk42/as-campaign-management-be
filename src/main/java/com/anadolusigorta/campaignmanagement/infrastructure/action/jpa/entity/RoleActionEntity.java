package com.anadolusigorta.campaignmanagement.infrastructure.action.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.action.model.RoleAction;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role_action")
public class RoleActionEntity extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "code", nullable = false)
    private Long code;

    public RoleAction toModel() {
        return RoleAction.builder()
                .id(super.getId())
                .name(name)
                .description(description)
                .code(code)
                .build();
    }

}
