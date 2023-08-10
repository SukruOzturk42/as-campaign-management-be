package com.anadolusigorta.campaignmanagement.infrastructure.menuitem.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.menuitem.model.MenuItem;
import com.anadolusigorta.campaignmanagement.domain.menuitem.model.SubMenuItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemResponse {

    private Long id;

    private String title;

    private String name;

    private String route;

    private String menuRoute;

    private String apiPath;

    private String icon;


    private List<MenuItemResponse> subMenuItems;

    private String auth;

    public static MenuItemResponse fromModel(MenuItem menuItem) {
        return MenuItemResponse.builder()
                .id(menuItem.getId())
                .title(menuItem.getTitle())
                .name(menuItem.getName())
                .route(menuItem.getRoute())
                .menuRoute(menuItem.getMenuRoute())
                .apiPath(menuItem.getApiPath())
                .icon(menuItem.getIcon())
                .subMenuItems(menuItem.getSubMenuItems() != null ?
                        menuItem.getSubMenuItems().stream()
                        .map(MenuItemResponse::fromModel)
                        .collect(Collectors.toList()) : null)
                .build();
    }

    public static MenuItemResponse fromModel(SubMenuItem menuItem) {
        return MenuItemResponse.builder()
                .id(menuItem.getId())
                .title(menuItem.getTitle())
                .name(menuItem.getName())
                .menuRoute(menuItem.getMenuRoute())
                .auth(menuItem.getAuth())
                .build();
    }

    public static List<MenuItemResponse> fromListOfModel(List<MenuItem> menuItems) {
        return menuItems
                .stream()
                .map(MenuItemResponse::fromModel)
                .collect(Collectors.toList());
    }


}
