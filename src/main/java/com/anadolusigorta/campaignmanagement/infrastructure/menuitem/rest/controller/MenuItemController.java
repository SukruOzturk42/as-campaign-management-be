package com.anadolusigorta.campaignmanagement.infrastructure.menuitem.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.menuitem.facade.MenuItemFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.menuitem.rest.dto.response.MenuItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@ApiManagementController
@RequiredArgsConstructor
public class MenuItemController extends BaseController {

    private final MenuItemFacade menuItemFacade;

    @GetMapping("menu-item")
    public Response<?> getMenuItemsByUserRoleName() {
        return respond(MenuItemResponse.fromListOfModel(menuItemFacade.getMenuItemsByRoleName()));
    }



}
