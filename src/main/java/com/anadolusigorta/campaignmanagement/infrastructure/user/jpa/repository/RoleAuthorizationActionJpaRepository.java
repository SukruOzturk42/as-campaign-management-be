package com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity.RoleAuthorizationActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleAuthorizationActionJpaRepository extends JpaRepository<RoleAuthorizationActionEntity, Long> {

    List<RoleAuthorizationActionEntity> findAllByUserRoleIdAndCampaignStatusIdAndCampaignApprovalStatusIdAndType(Long userRoleId,
            Long campaignStatusId,
            Long campaignApprovalStatusId,String type);


    List<RoleAuthorizationActionEntity> findAllByUserRoleNameAndCampaignStatusIdAndCampaignApprovalStatusIdAndType(String userRoleName,
                                                                                                                 Long campaignStatusId,
                                                                                                                 Long campaignApprovalStatusId,String type);

    Optional<RoleAuthorizationActionEntity> findByUserRoleNameAndIsInitialActionTrue(String roleName);


    Optional<RoleAuthorizationActionEntity> findByUserRoleIdAndIsInitialActionTrue(Long roleId);

    Optional<RoleAuthorizationActionEntity> findFirstByUserRoleNameAndRoleActionName(String roleName,String actionName);

}
