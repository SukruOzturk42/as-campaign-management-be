package com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.user.model.UserRole;
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
@Table(name = "user_role")
public class UserRoleEntity extends AbstractEntity {

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = false, length = 400)
    private String description;

    @Column(name = "code", nullable = false)
    private Long code;

    @Column(name = "department_code")
    private String departmentCode;

    @Column(name = "title")
    private String title;

    @Column(name = "title_code")
    private String titleCode;

    @Column(name = "auth")
    private String auth;

    public UserRole toModel() {
        return UserRole.builder()
                .name(name)
                .description(description)
                .code(code)
                .auth(auth)
                .build();
    }

}
