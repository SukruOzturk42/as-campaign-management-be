package com.anadolusigorta.campaignmanagement.domain.menuitem.facade;

import com.anadolusigorta.campaignmanagement.domain.menuitem.model.MenuItem;
import com.anadolusigorta.campaignmanagement.domain.menuitem.model.SubMenuItem;
import com.anadolusigorta.campaignmanagement.domain.menuitem.port.MenuItemRepository;
import com.anadolusigorta.campaignmanagement.domain.user.facade.UserSecurityFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemFacade {

    private final MenuItemRepository menuItemRepository;
    private final UserSecurityFacade userSecurityFacade;

    public List<MenuItem> getMenuItemsByRoleName() {
        var roleName = userSecurityFacade.getUserRole().getName();
        return menuItemRepository.findMenuItemsByRoleName(roleName);
    }



}
