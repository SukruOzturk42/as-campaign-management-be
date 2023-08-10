package com.anadolusigorta.campaignmanagement.domain.menuitem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    private Long id;

    private String title;

    private String name;

    private String route;
    
    private String menuRoute;

    private String apiPath;

    private String icon;

    private MenuItem parent;

    private List<SubMenuItem> subMenuItems;

    private Integer order;

}
