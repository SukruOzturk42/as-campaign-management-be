package com.anadolusigorta.campaignmanagement.domain.user.port;

import com.anadolusigorta.campaignmanagement.domain.user.model.UserRole;

import java.util.List;

public interface UserRoleRepository {

    List<UserRole> getAllUserRole();

}
