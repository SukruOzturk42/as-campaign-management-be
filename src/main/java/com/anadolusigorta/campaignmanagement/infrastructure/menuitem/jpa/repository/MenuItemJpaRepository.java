package com.anadolusigorta.campaignmanagement.infrastructure.menuitem.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.menuitem.jpa.entity.MenuItemEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemJpaRepository extends JpaRepository<MenuItemEntity, Long> {

}
