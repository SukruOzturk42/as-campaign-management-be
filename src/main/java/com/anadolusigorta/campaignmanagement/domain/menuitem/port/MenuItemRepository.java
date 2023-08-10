package com.anadolusigorta.campaignmanagement.domain.menuitem.port;

import com.anadolusigorta.campaignmanagement.domain.menuitem.model.MenuItem;
import com.anadolusigorta.campaignmanagement.domain.menuitem.model.SubMenuItem;

import java.util.List;

public interface MenuItemRepository {

    List<MenuItem> findMenuItemsByRoleName(String roleName);





}
