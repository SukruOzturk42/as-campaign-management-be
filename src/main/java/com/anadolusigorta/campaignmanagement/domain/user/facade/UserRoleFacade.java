package com.anadolusigorta.campaignmanagement.domain.user.facade;

import com.anadolusigorta.campaignmanagement.domain.user.model.UserRole;
import com.anadolusigorta.campaignmanagement.domain.user.port.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleFacade {

    private final UserRoleRepository userRoleRepository;

    public List<UserRole> getAllUserRole(){
        return userRoleRepository.getAllUserRole();
    }

}
