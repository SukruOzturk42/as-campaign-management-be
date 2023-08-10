package com.anadolusigorta.campaignmanagement.infrastructure.menuitem.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.menuitem.model.MenuItem;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity.UserRoleEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "menu_item")
public class MenuItemEntity extends AbstractEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "name")
    private String name;

    @Column(name = "route")
    private String route;

    @Column(name = "menu_route")
    private String menuRoute;

    @Column(name = "api_path")
    private String apiPath;

    @Column(name = "icon")
    private String icon;

    @Column(name = "order_count")
    private Integer order;


}
