package com.anadolusigorta.campaignmanagement.infrastructure.menuitem.jpa.entity;

import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity.UserRoleEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "role_menu_item")
public class RoleMenuItemEntity  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserRoleEntity role;

    @ManyToOne
    private MenuItemEntity menuItem;

    @ManyToOne
    private SubMenuItemEntity subMenuItem;

    @Column(name = "auth")
    private String auth;



}
