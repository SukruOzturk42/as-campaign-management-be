package com.anadolusigorta.campaignmanagement.domain.user.facade;

import com.anadolusigorta.campaignmanagement.domain.user.model.RoleAuthorizationAction;
import com.anadolusigorta.campaignmanagement.domain.user.port.RoleAuthorizationActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleAuthorizationActionFacade {
    private final RoleAuthorizationActionRepository roleAuthorizationActionRepository;
    private final UserSecurityFacade userSecurityFacade;

    public List<RoleAuthorizationAction> getRoleAuthorizationActions(RoleAuthorizationAction roleAuthorizationAction) {
        return roleAuthorizationActionRepository.getRoleAuthorizationActions(roleAuthorizationAction);
    }

    public List<RoleAuthorizationAction> getRoleAuthorizations(RoleAuthorizationAction roleAuthorizationAction) {
        roleAuthorizationAction.setUserRoleName(userSecurityFacade.getUserRole().getName());
        return roleAuthorizationActionRepository.getRoleAuthorizations(roleAuthorizationAction);
    }

}
