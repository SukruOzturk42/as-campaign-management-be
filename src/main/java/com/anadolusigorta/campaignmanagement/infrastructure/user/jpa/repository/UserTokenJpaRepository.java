package com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity.UserRoleEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserTokenJpaRepository extends JpaRepository<UserTokenEntity, Long> {

       Optional<UserTokenEntity> findByToken(String token);

       List<UserTokenEntity> findByUserName(String userName);

       void deleteByToken(String token);

      void deleteByExpireTimeBefore(LocalDateTime time);
      void deleteByExpireTimeAfter(LocalDateTime time);



}
