package com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.user.model.RoleAuthorizationAction;
import com.anadolusigorta.campaignmanagement.domain.user.port.RoleAuthorizationActionRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity.RoleAuthorizationActionEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.repository.RoleAuthorizationActionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleAuthorizationActionJpaAdapter implements RoleAuthorizationActionRepository {
    private final RoleAuthorizationActionJpaRepository roleAuthorizationActionJpaRepository;



    @Override
    public List<RoleAuthorizationAction> getRoleAuthorizationActions(
            RoleAuthorizationAction roleAuthorizationAction) {
        List<RoleAuthorizationActionEntity> roleAuthorizationActionEntities = roleAuthorizationActionJpaRepository
                .findAllByUserRoleNameAndCampaignStatusIdAndCampaignApprovalStatusIdAndType(roleAuthorizationAction.getUserRoleName(),
                        roleAuthorizationAction.getCampaignStatusId(),
                        roleAuthorizationAction.getCampaignApprovalStatusId(),"action");
        return roleAuthorizationActionEntities.stream().map(RoleAuthorizationActionEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleAuthorizationAction> getRoleAuthorizations(
            RoleAuthorizationAction roleAuthorizationAction) {
        return null;
    }

    public RoleAuthorizationAction getRoleAuthorizationActionById(long id) {

        return roleAuthorizationActionJpaRepository.findById(id)
                .orElseThrow(()->new CampaignManagementException(ExceptionConstants.ROLE_ACTION_NOT_FOUND)).toModel();
    }

    public RoleAuthorizationAction getInitialRoleAuthorizationActionByRoleName(String roleName) {

        return roleAuthorizationActionJpaRepository.findByUserRoleNameAndIsInitialActionTrue(roleName)
                .orElseThrow(()->new CampaignManagementException(ExceptionConstants.ROLE_ACTION_NOT_FOUND)).toModel();
    }

    public RoleAuthorizationAction getInitialRoleAuthorizationActionByRoleId(String roleName) {

        return roleAuthorizationActionJpaRepository
                .findByUserRoleNameAndIsInitialActionTrue(roleName)
                .orElseThrow(()->new CampaignManagementException(ExceptionConstants.ROLE_ACTION_NOT_FOUND)).toModel();
    }
}
