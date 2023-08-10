package com.anadolusigorta.campaignmanagement.infrastructure.user.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.user.facade.UserRoleFacade;
import com.anadolusigorta.campaignmanagement.domain.user.facade.UserSecurityFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@ApiManagementController
@RequiredArgsConstructor
public class UserRoleController extends BaseController {

    private final UserSecurityFacade userSecurityFacade;
    private final UserRoleFacade userRoleFacade;

    @GetMapping("active-user")
    public Response<String> getActiveUser() {
        return respond(userSecurityFacade.getUserUserName());

    }

    @GetMapping("user-roles")
    public Response<?> getAllUserRole() {
        return respond(userRoleFacade.getAllUserRole());

    }

}
