package com.anadolusigorta.campaignmanagement.infrastructure.menuitem.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.menuitem.jpa.entity.RoleMenuItemEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleMenuItemJpaRepository extends JpaRepository<RoleMenuItemEntity, Long> {

	List<RoleMenuItemEntity> findByRole(UserRoleEntity userRole);
}
