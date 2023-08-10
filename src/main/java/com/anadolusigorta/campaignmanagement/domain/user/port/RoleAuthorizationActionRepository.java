package com.anadolusigorta.campaignmanagement.domain.user.port;

import com.anadolusigorta.campaignmanagement.domain.user.model.RoleAuthorizationAction;

import java.util.List;

public interface RoleAuthorizationActionRepository {

    List<RoleAuthorizationAction> getRoleAuthorizationActions(RoleAuthorizationAction roleAuthorizationAction);

    List<RoleAuthorizationAction> getRoleAuthorizations(RoleAuthorizationAction roleAuthorizationAction);

}
