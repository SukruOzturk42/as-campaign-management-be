package com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRoleJpaRepository extends JpaRepository<UserRoleEntity, Long> {

       Optional<UserRoleEntity> findByDepartmentCodeContainingAndTitleCodeContaining(String code,String titleCode);
       Optional<UserRoleEntity> findByDepartmentCodeContainingAndTitleCodeIsNull(String code);
       Optional<UserRoleEntity> findByName(String name);


}
