package com.anadolusigorta.campaignmanagement.infrastructure.user.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.user.facade.RoleAuthorizationActionFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.user.rest.dto.request.RoleAuthorizationActionRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.user.rest.dto.response.RoleAuthorizationActionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ApiManagementController
@RequiredArgsConstructor
public class RoleAuthorizationActionController extends BaseController {

    private final RoleAuthorizationActionFacade roleAuthorizationActionFacade;

    @PostMapping("role-authorization-action")
    public Response<?> getRoleAuthorizationActions(@RequestBody RoleAuthorizationActionRequest roleAuthorizationActionRequest) {
        var roleAuthorizationActions = roleAuthorizationActionFacade.getRoleAuthorizationActions(roleAuthorizationActionRequest.toModel());
        return respond(RoleAuthorizationActionResponse.fromListOfModel(roleAuthorizationActions));
    }

    @PostMapping("role-authorization")
    public Response<?> getRoleAuthorizations(@RequestBody RoleAuthorizationActionRequest roleAuthorizationActionRequest) {
        var roleAuthorizationActions = roleAuthorizationActionFacade.getRoleAuthorizations(roleAuthorizationActionRequest.toModel());
        return respond(RoleAuthorizationActionResponse.fromListOfModel(roleAuthorizationActions));
    }

}
