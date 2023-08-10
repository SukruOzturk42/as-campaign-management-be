package com.anadolusigorta.campaignmanagement.infrastructure.menuitem.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.menuitem.model.MenuItem;
import com.anadolusigorta.campaignmanagement.domain.menuitem.model.SubMenuItem;
import com.anadolusigorta.campaignmanagement.domain.menuitem.port.MenuItemRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.menuitem.jpa.entity.MenuItemEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.menuitem.jpa.entity.RoleMenuItemEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.menuitem.jpa.entity.SubMenuItemEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.menuitem.jpa.repository.MenuItemJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.menuitem.jpa.repository.RoleMenuItemJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.menuitem.jpa.repository.SubMenuItemJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.repository.UserRoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class MenuItemJpaAdapter implements MenuItemRepository {

    private final UserRoleJpaRepository userRoleJpaRepository;
	private final RoleMenuItemJpaRepository roleMenuItemJpaRepository;


	@Override
	public List<MenuItem> findMenuItemsByRoleName(String roleName) {

		var menuItems=new ArrayList<MenuItem>();

		var role=userRoleJpaRepository.findByName(roleName)
				.orElseThrow(()->new CampaignManagementException("role.not.found"));
		var menus=roleMenuItemJpaRepository.findByRole(role);
		var menuGroups=menus
				.stream()
				.collect(groupingBy(RoleMenuItemEntity::getMenuItem));

		menuGroups.keySet().forEach(item->{

			var subMenus=menuGroups.get(item)
					.stream()
					.map(t->t.getSubMenuItem().toModel(t.getAuth()))
					.sorted(Comparator.comparing(SubMenuItem::getOrder))
					.collect(Collectors.toList());

			var menuItem=MenuItem.builder()
					.menuRoute(item.getMenuRoute())
					.title(item.getTitle())
					.name(item.getName())
					.apiPath(item.getApiPath())
					.route(item.getRoute())
					.id(item.getId())
					.icon(item.getIcon())
					.subMenuItems(subMenus)
					.order(item.getOrder())
					.build();
			menuItems.add(menuItem);
		});
        menuItems.sort(Comparator.comparing(MenuItem::getOrder));
		return menuItems;
	}


}
