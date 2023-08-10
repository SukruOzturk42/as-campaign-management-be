package com.anadolusigorta.campaignmanagement.infrastructure.menuitem.jpa.entity;


import com.anadolusigorta.campaignmanagement.domain.menuitem.model.SubMenuItem;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "menu_sub_item")
public class SubMenuItemEntity  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "name")
    private String name;

    @Column(name = "menu_route")
    private String menuRoute;

    @Column(name = "order_count")
    private Integer order;



    public SubMenuItem toModel(String auth) {
        return SubMenuItem.builder()
                .id(id)
                .title(title)
                .name(name)
                .menuRoute(menuRoute)
                .auth(auth)
                .order(order)
                .build();
    }

}
