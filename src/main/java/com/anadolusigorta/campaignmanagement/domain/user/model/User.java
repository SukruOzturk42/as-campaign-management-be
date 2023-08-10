package com.anadolusigorta.campaignmanagement.domain.user.model;

import com.anadolusigorta.campaignmanagement.domain.menuitem.model.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username;
    private String fullName;
    private String userTitle;
    private UserRole userRole;
    private Long organizationCode;
    private String agencyCode;
    private String mail;
    private String userRegionCode;
    private String employeeNumber;
    private List<String> menuItems;




}
